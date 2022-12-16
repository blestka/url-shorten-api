# API for urls shortening

## API provides the following endpoints:


### POST /short-urls - endpoint for urls shorten

Request body example:

{
    "url" : "http://apple.com"
}

url - mandatory field

Response example

{
    "shortUrl": "http://localhost:8080/IcD28LOoHz"
}

### GET /{hash} - access original url by short url

As a result caller would be redirected to the original url

### GET /stats - returns full endpoints usage statistics

Response example:

[
{
"origUrl": "http://apple.com",
"operation": "SHORTEN",
"count": 3
},
{
"origUrl": "http://apple.com",
"operation": "READ",
"count": 1
},
{
"origUrl": "http://google.com",
"operation": "SHORTEN",
"count": 3
}
]

# Local development

Requirements:
Java 17+
Mysql 8+

1. Clone the repo from github: https://github.com/blestka/url-shortener-api.git
2. Setup mysql database with password "root" for root user locally. Apply V1__urls-table.sql to create and setup database.
3. Run "./gradlew clean build bootRun" from project root. It should start url-shortener app at port 8080: http://localhost:8080/short-urls.