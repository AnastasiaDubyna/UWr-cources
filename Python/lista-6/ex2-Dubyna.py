from bs4 import BeautifulSoup
import requests
import difflib
import time

SOURCES = ["https://www.nytimes.com/spotlight/learning-current-events", "https://money.cnn.com/data/markets"]

source = BeautifulSoup(requests.get("https://www.timeanddate.com/worldclock/poland").text, "lxml").body
time.sleep(10)
source_2 = BeautifulSoup(requests.get("https://www.timeanddate.com/worldclock/poland").text, "lxml").body
# difference = list(difflib.Differ().compare(source, source_2))
