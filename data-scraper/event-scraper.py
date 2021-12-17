# **************************************************************************** #
#                                                                              #
#    event-scraper.py                                                          #
#    License: GPLv3                                                            #
#    By: hamza <contact@codeplumbers.eu>                                       #
#                                                                              #
#    Created: 2021/12/18 00:13:04 by hamza                                     #
#    Updated: 2021/12/18 00:13:04 by hamza                                     #
#                                                                              #
# **************************************************************************** #

# -*- coding: utf-8 -*-
from bs4 import BeautifulSoup
import requests
import json
import re

headers = {'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9'}
url = 'https://www.juntadeandalucia.es/cultura/agendaculturaldeandalucia/GRANADA'

events = []

# Get the total number of events
response=requests.get(url,headers=headers)
soup=BeautifulSoup(response.content,'lxml')
total_events = 0
total_pages = 0

total_events_div = soup.select('.total-eventos')[0]
try:
    total_events = int(total_events_div.get_text().split(": ")[1])
    total_pages = int(total_events / 10)
    print(f"Total events: {total_events} (pages: {total_pages})")
except Exception as e:
    raise e

for page in range(total_pages):
    print(f"Getting Page {page}..")
    page_url = url + f"?page={page}"

    response=requests.get(page_url,headers=headers)
    soup=BeautifulSoup(response.content,'lxml')

    for item in soup.select(".elemento-listado"):
        event = {}
        # get event image
        image_url = item.select_one(".imagen a:first-child").select_one(".image1")["src"]
        event["image"] = image_url
        # get location
        location_text = item.select_one(".textos div:first-child").get_text()
        event["location"] = re.sub(r'\s', '', str(location_text))

        # get date
        date_text = item.select_one(".textos > .fecha").get_text()
        event["date"] = str(date_text).strip()

        # get title
        title_tag = item.select_one(".textos > .titulo")
        title_text = title_tag.get_text()
        event["title"] = str(title_text).strip()

        # Get href
        link_url = title_tag.select_one("a:first-child")["href"]
        event["url"] = str(link_url).strip()

        events.append(event)

# write events.json file
with open("events.json", "w") as f:
    json.dump(events, f, ensure_ascii=False)