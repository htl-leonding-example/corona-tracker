# corona-tracker

- Small app for registering visitors @ htl-leonding
- The form data is stored in the local storage of the web browser.

## Endpoints

- <server>/api/listentry/csv
- <server>/api/listentry

## Deployment

Just commit and push - a gh-actions-pipeline deploys the app + db

## Local Build

- there is a fat-jar used (uber-jar)

```
mvn clean package -Dquarkus.package.type=uber-jar
```

