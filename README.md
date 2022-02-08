# stock-profit-scanner

Stock profit scanner was created to find optimal points (% threshold from local MIN/MAX) to buy/sell stocks (like S&P500, Nasdaq etc) and outperform the market or time the market. 
What is possible in theory only :)

DISCLAIMER: IT'S NOT A RECOMMENDATION FOR TRADE! USE THIS FOR YOUR OWN RESPONSIBILITY AND RISK!!!

### S&P 500 output example:
- Historical stock data resource: spy.csv
- Threshold price to BUY was reached (Mon Jun 19 16:00:00 EDT 1995): 54.48$, from local MIN of: 42.82$
- Threshold price to SELL was reached (Mon Mar 12 16:00:00 EST 2001): 120.32$ from local MAX of: 155.75$
- Threshold price to BUY was reached (Mon Jun 02 16:00:00 EDT 2003): 98.08$, from local MIN of: 77.07$
- Threshold price to SELL was reached (Tue Jul 15 16:00:00 EDT 2008): 121.69$ from local MAX of: 157.53$
- Threshold price to BUY was reached (Thu Apr 09 16:00:00 EDT 2009): 85.39$, from local MIN of: 67.1$
- Threshold price to SELL was reached (Thu Mar 12 16:00:00 EDT 2020): 261.94$ from local MAX of: 339.08$
- Threshold price to BUY was reached (Thu Apr 09 16:00:00 EDT 2020): 277.74$, from local MIN of: 218.26$
- ### Portfolio started at: Fri Jan 29 16:00:00 EST 1993
- ### Portfolio closed at: Thu Apr 15 16:00:00 EDT 2021
- ### Market performance: 946.03%
- ### Portfolio MAX performance: 1255.89%, to buy at: 27.25% from local MIN, to sell at: 22.75% from local MAX


### Nasdaq output example:
- Historical stock data resource: qqq.csv
- Threshold price to BUY was reached (Mon Jul 12 16:00:00 EDT 1999): 60.02$, from local MIN of: 48.3$
- Threshold price to SELL was reached (Tue Apr 04 16:00:00 EDT 2000): 94.58$ from local MAX of: 120.49$
- Threshold price to BUY was reached (Fri Jun 02 16:00:00 EDT 2000): 91.94$, from local MIN of: 72.38$
- Threshold price to SELL was reached (Mon Oct 09 16:00:00 EDT 2000): 81.25$ from local MAX of: 103.5$
- Threshold price to BUY was reached (Wed Jan 03 16:00:00 EST 2001): 65.24$, from local MIN of: 52.5$
- Threshold price to SELL was reached (Wed Feb 14 16:00:00 EST 2001): 54.24$ from local MAX of: 69.1$
- Threshold price to BUY was reached (Wed Apr 11 16:00:00 EDT 2001): 41.75$, from local MIN of: 33.61$
- Threshold price to SELL was reached (Tue Jul 10 16:00:00 EDT 2001): 40.47$ from local MAX of: 51.55$
- Threshold price to BUY was reached (Thu Oct 11 16:00:00 EDT 2001): 33.84$, from local MIN of: 27.24$
- Threshold price to SELL was reached (Thu Feb 21 16:00:00 EST 2002): 33.95$ from local MAX of: 43.25$
- Threshold price to BUY was reached (Wed Oct 23 16:00:00 EDT 2002): 24.56$, from local MIN of: 19.77$
- Threshold price to SELL was reached (Tue Jan 22 16:00:00 EST 2008): 43.23$ from local MAX of: 55.08$
- Threshold price to BUY was reached (Fri Jan 02 16:00:00 EST 2009): 31.13$, from local MIN of: 25.06$
- Threshold price to SELL was reached (Mon Aug 24 16:00:00 EDT 2015): 89.8$ from local MAX of: 114.4$
- Threshold price to BUY was reached (Fri Oct 23 16:00:00 EDT 2015): 112.07$, from local MIN of: 89.8$
- Threshold price to SELL was reached (Fri Dec 21 16:00:00 EST 2018): 147.22$ from local MAX of: 187.54$
- Threshold price to BUY was reached (Fri Mar 15 16:00:00 EDT 2019): 178.25$, from local MIN of: 143.47$
- Threshold price to SELL was reached (Thu Mar 12 16:00:00 EDT 2020): 186.42$ from local MAX of: 237.47$
- Threshold price to BUY was reached (Tue Apr 14 16:00:00 EDT 2020): 206.42$, from local MIN of: 164.94$
- ### Portfolio started at: Wed Mar 10 16:00:00 EST 1999
- ### Portfolio closed at: Thu Apr 15 16:00:00 EDT 2021
- ### Market performance: 665.46%
- ### Portfolio MAX performance: 1290.92%, to buy at: 24.25% from local MIN, to sell at: 21.5% from local MAX
