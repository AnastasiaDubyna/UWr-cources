from bs4 import BeautifulSoup
from bs4.element import Comment
import requests
import re
'''
Hello! 

This code is still far from perfect. I have a problem with findPythonWord function.
I can't figure out how to make it work. I tried several implementations but always end up with 
an empty array or an array full of random elements
I would be really greatfull for any advice on how to fix it because right now I am really close to a
mental breakdown -_-

'''

def get_page_soup(url):
    '''
    Takes url
    Returns beautifulsoup object
    '''
    html_page = requests.get(url).text

    return BeautifulSoup(html_page, "html.parser")
    
def get_links(soup):
    '''
    Takes BeautifulSoup object
    Returns list of http and https links
    '''
    links = []
    for link in soup.find_all('a', attrs={'href': re.compile("^http[s]*://")}):
        links.append(link.get('href'))
    return links

def crawl(start_page_url, distance, foo):
    '''
    Takes website url, int and function
    Returns list of tuples
    '''
    links = [start_page_url]
    links.extend(get_links(get_page_soup(start_page_url)))
    print(links)
    return action(links[:distance + 1], foo)

def action(links, foo):
    '''
    Takes list of links and a function
    Returns list of tuples (link, result of the function applied to a content of webpage)
    '''
    print(links)
    result = []
    for i in range(len(links)):
        page_soup = get_page_soup(links[i])
        result.append((links[i], foo(page_soup)))
    return result 

def tag_visible(element):
    if element.parent.name in ['style', 'script', 'head', 'title', 'meta', '[document]']:
        return False
    if isinstance(element, Comment):
        return False
    return True

def text_from_soup(soup):
    texts = soup.findAll(text=True)
    visible_texts = filter(tag_visible, texts) 

    return ' '.join(list(filter(None, [t.strip() for t in visible_texts])))


def findPythonWord(page_soup):
    '''
    Takes beautifulsoup object 
    Returns list of sentences which contain "Python" string

    '''
    text = text_from_soup(page_soup)
    # print(text)
    return re.findall(re.compile("^[^.!?\n]*Python[^.!?\n]*[.!?]$"), text)

    # return page_soup.body.find_all(text=re.compile("^[^.!?\n]*Python[^.!?\n]*[.!?]$"))
    # string=re.compile("^[^.!?\n]*Python[^.!?\n]*[.!?]$")
    # ^[A-Za-z,;'\"\]*Python+[A-Za-z,;'\"\]*[.?!]$
    # [^.]*?Python[^.]*\.
    # [A-Za-z\-,:;')\d\|(\"\\\s]*Python[A-Za-z\-,:;')\d\|(\"\\\s]*[.?!]$
    #  ^\s+[^.!?]*Python[^.!?]*[.!?]$
    # ^[^.!?]*Python[^.!?]*[.!?]$
    # [^.!?]*Python[^.!?]*[.!?]$
    # ^[^.!?\n]*Python[^.!?\n]*[.!?]$


test_html_1 = get_page_soup("https://www.geeksforgeeks.org/python-beautifulsoup-find-all-class/")
# print(test_html_1)
# print(findPythonWord(test_html_1))
# print(get_links(get_page_soup("https://uni.wroc.pl/")))

# print(crawl("https://uni.wroc.pl/", 4, findPythonWord)) #No Python mention in that distance
# print(crawl("https://zapisy.ii.uni.wroc.pl/offer/jezyk-python-w-komercyjnych-projektach/", 4, findPythonWord))
for tuple in crawl("https://zapisy.ii.uni.wroc.pl/offer/jezyk-python-w-komercyjnych-projektach/", 4, findPythonWord):
    print(tuple)
 #There is Python