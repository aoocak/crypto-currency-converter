# crypto-currency-converter
A JAVA web application to fetch the current localized price of a cryptocurrency.

# assumptions for localization from ip address

Java has a built-in library for currency formatting, works with a locale parameter to determine the currency symbol is either on left or right or will be CA$ or CAD etc.
To get the locale I needed to things: a language and a country code. 

I've found a free api to get country code and languages from an ip address.
Then created a locale object to pass Numberformat.getCurrencyInstance() as a parameter.

Then used it as a currency formatter with given unformatted price and get the right fiat code from this object.

# assumptions for coin conversion 

I have used coinmarketcap free api to accomplish one-to-one conversion. 
To other ideas, fetch all data with a cronjob works every minute, store it in cash, everytime when controller gets a request, returns immediately if it is in the cash.

# about ci/cd
ci/cd workflows on github actions, I have used a workflow that runs test with every push and/or pr.

