import lxml
import requests
from bs4 import BeautifulSoup
import matplotlib.pyplot as plt
import matplotlib.dates as mdates

import pandas as pd
import csv
import re

"""
Hello! 
Some comments from me: 
0. Please download from skos "ex1-Dubyna-data.zip".
1. I wrote a web scraper to retrieve covid cases data and looks like it works just fine. But in case
   of any unexpected errors I'll attach csv files with all data prepared. 
2. Temperature data was not so easy to deal with, so I just attached in the same zip file raw csv tables. 
   I extract all temperature measurements, count average and add it to cases files. 
   Raw csv files here: https://danepubliczne.imgw.pl/datastore
   As Maciej Witkowski stated on Teams, dealing with data from this government meteorological institution 
   is pretty painful, but here's a quick "how-to":
   a. "Dane archiwalne" -> "Dane meteorologiczne" -> 2020
   b. In downloaded zip archive you'll need a file under a name "B00300S". It's a code for air temperature. 
   c. Inside you'll find over a million rows of measurements formatted as 
      "meteorological-station-code; data-type-code; date-and-time; temperature in C"
Warning: it might take 10-20 second to create these data files 
3. For my graphs I chose two time periods: 
   a. July - September 2020, right before a second wave of the pandemic started
   b. October - December 2020, second wave of the pandemic 
"""


def create_cases_files():
    """
    creates csv files with these data: date, confirmed daily cases, average temperature
    Here I'm using requests and beautiful soup packages to extract data from a table on wikipedia page.
    Then I'm creating csv files for each of the two time periods.
    At the end, add_temperature_data function called and temperatures added to the files.
    """
    cases_url = requests.get("https://en.wikipedia.org/wiki/Template:COVID-19_pandemic_data"
                             "/Poland_medical_cases_by_voivodeship#cite_note-602").text
    soup = BeautifulSoup(cases_url, "lxml")
    my_table = soup.find_all("table")[2]
    table_rows = my_table.find_all("tr")
    pattern = r'\[.*?\]'
    del table_rows[-3:]
    del table_rows[:6]

    wave_file = open("./cases_second_wave.csv", "w", encoding="UTF8", newline="")
    writer_wave = csv.writer(wave_file)
    writer_wave.writerow(["Date", "Cases"])

    no_wave_file = open("./cases_before_second_wave.csv", "w", encoding="UTF8", newline="")
    writer_no_wave = csv.writer(no_wave_file)
    writer_no_wave.writerow(["Date", "Cases"])

    for row in table_rows:
        cells = row.find_all("td")
        date_cell = cells[0].get_text().strip('\n')
        cases_cell = int(re.sub(pattern, '', cells[4].get_text()).strip('\n').replace(",", ""))

        if "November" in date_cell or "December" in date_cell or "October" in date_cell:
            writer_wave.writerow([date_cell, cases_cell])
        if "September" in date_cell or "August" in date_cell or "July" in date_cell:
            writer_no_wave.writerow([date_cell, cases_cell])

    wave_file.close()
    no_wave_file.close()
    add_temperature_data()


def add_temperature_data():
    """
    Adds to created csv tables temperature column
    """
    no_wave_file_names = ["july-2020-temp-raw.csv",
                          "august-2020-temp-raw.csv",
                          "september-2020-temp-raw.csv",
                          ]
    second_wave_file_names = ["october-2020-temp-raw.csv",
                              "november-2020-temp-raw.csv",
                              "december-2020-temp-raw.csv"
                              ]
    no_wave_temp_measurements, wave_temp_measurements = [get_average_temperature(no_wave_file_names),
                                                         get_average_temperature(second_wave_file_names)]
    before_wave_csv = pd.read_csv('cases_before_second_wave.csv')
    before_wave_csv['Temperature'] = no_wave_temp_measurements
    before_wave_csv.to_csv('cases_before_second_wave.csv', index=False)

    wave_csv = pd.read_csv('cases_second_wave.csv')
    wave_csv['Temperature'] = wave_temp_measurements
    wave_csv.to_csv('cases_second_wave.csv', index=False)


def get_average_temperature(file_names):
    """
    Extracts temperature measurements from existing files, counts average for this day
    :param file_names: files where data stored
    :return: list of numbers
    """
    measurements = {}
    for name in file_names:
        with open(name, "r") as file:
            for row in file:
                splited = row.split(";")
                date = splited[2].split(" ")[0]
                temperature = float(splited[3].replace(",", "."))
                if date in measurements:
                    measurements[date].append(temperature)
                else:
                    measurements[date] = [temperature]
    return [sum(measurements[key]) / len(measurements[key]) for key in measurements]


def create_plot(file_name, plot_title):
    """
    Creates plots, sets labels, ranges, second y axes, grid, plot title, colors.
    """
    pd.plotting.register_matplotlib_converters()
    with open(file_name, "r") as file:
        plot_data = pd.read_csv(file, parse_dates=["Date"])
    fig, ax = plt.subplots()
    ax.plot(plot_data.Date, plot_data.Cases, color="b")
    ax.set_xlabel("Date")
    ax.set_ylabel("Daily cases", color="b")
    ax.set_ylim([0, 30000])
    ax.grid(True)
    ax.set_title(plot_title, fontsize='medium')
    ax.xaxis.set_major_formatter(mdates.DateFormatter('%d-%b'))
    for label in ax.get_xticklabels(which='major'):
        label.set(rotation=30, horizontalalignment='right')
    ax2 = ax.twinx()
    ax2.plot(plot_data.Date, plot_data.Temperature, color='g')
    ax2.set_ylabel("Temperature[C]", color="g")
    ax2.set_ylim([-5, 30])
    fig.tight_layout()


def run():
    create_cases_files()
    create_plot("cases_before_second_wave.csv",
                "Covid19 cases and daily temperature before a second wave of the pandemic")
    create_plot("cases_second_wave.csv", "Covid19 cases and daily temperature during a second wave of the pandemic")
    plt.show()


run()
