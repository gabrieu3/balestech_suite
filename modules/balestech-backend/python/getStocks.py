import yfinance as yf
import pandas as pd
import investpy as inv


br = inv.stocks.get_stocks(country='brazil')

for a in br['symbol']:
    #if len(a) <= 5:
    print(a)
