# Open API presentation tech notes 1

--------------------------------
--------------------------------
--------------------------------

```sh
docker-compose -f ./spring-petstore/src/main/resources/docker/docker-compose.yml up -d
```

--------------------------------

[backend]

```sh
openapi-generator generate -i openapi_openapitools-github_3.0.0.yaml -g spring -o spring-petstore/
```

```
cd spring-petstore/
```

```sh
mvn clean install
```

```sh
mvn spring-boot:run
```

```sh
http://localhost:8080
```

```sh
ctr+c
```

```sh
cd ..
```

--------------------------------

[frontend]

```sh
ng new angular-petstore
```

```sh
? Would you like to enable autocompletion? This will set up your terminal so pressing TAB while typing Angular CLI commands will show possible options and autocomplete arguments. (Enabling
autocompletion will modify configuration files in your home directory.) Yes
Appended `source <(ng completion script)` to `/Users/kuzmanov/.zshrc`. Restart your terminal or run the following to autocomplete `ng` commands:
    source <(ng completion script)
? Would you like to add Angular routing? Yes
? Which stylesheet format would you like to use? CSS
```

```sh
### This command generates services.
ng-openapi-gen --input openapi_openapitools-github_3.0.0.yaml --output angular-petstore/src/app/api
```

```sh
cd angular-petstore
```

```sh

npm install
```

```sh
npm run build
```

```sh
ng build
```

```sh
ng serve
```

---

[frontend-testing]

```sh
docker-compose -f ./src/main/resources/docker/docker-compose.yml up -d
```

```sh
mvn spring-boot:run
```

```sh
http://localhost:8080
```

```sh
POST /pet DOG

{
  "category": {
    "id": 1,
    "name": "indoor-pet"
  },
  "id": 1,
  "name": "Dodge",
  "photoUrls": [
    "../../assets/images/dog.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 1,
      "name": "dog"
    }
  ]
}
```

```json
{
  "category": {
    "id": 0,
    "name": "string"
  },
  "id": 0,
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "status": "available",
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ]
}
```

```sh
POST /pet CAT

{
  "category": {
    "id": 1,
    "name": "indoor-pet"
  },
  "id": 2,
  "name": "Tabby",
  "photoUrls": [
    "../../assets/images/cat.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 2,
      "name": "cat"
    }
  ]
}
```

```sh
POST /pet WOLF

{
  "category": {
    "id": 2,
    "name": "outdoor-pet"
  },
  "id": 3,
  "name": "Wolfy",
  "photoUrls": [
    "../../assets/images/wolf.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 3,
      "name": "wolf"
    }
  ]
}
```

```sh
cd angular-petstore
```

```sh
npm run test_mocha
```

```sh
ng test
```

--------------------------------

```sh
ng test --include src/spec/openapivalidation.spec.ts
```

---

```sh
ng test --include src/app/petstore/petstore.component.spec.ts
```

```sh
ng test --main src/spec/openapivalidation.spec.ts
```

--------------------------------

```sh
npm install --save mocha

npm install --save supertest

npm install --save chai

npm install --save express-openapi-validate
```

```sh
npm run test_mocha
```

--------------------------------
[dummy data]

```json
{
  "category": {
    "id": 1,
    "name": "indoor-pet"
  },
  "id": 0,
  "name": "Test Dodge",
  "photoUrls": [
    "../../assets/images/dog.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 1,
      "name": "dog"
    }
  ]
}
```

```json
{
  "category": {
    "id": 1,
    "name": "indoor-pet"
  },
  "id": 1,
  "name": "Dodge",
  "photoUrls": [
    "../../assets/images/dog.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 1,
      "name": "dog"
    }
  ]
}
```

```json
{
  "category": {
    "id": 1,
    "name": "indoor-pet"
  },
  "id": 2,
  "name": "Tabby",
  "photoUrls": [
    "../../assets/images/cat.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 2,
      "name": "cat"
    }
  ]
}
```

```json
{
  "category": {
    "id": 2,
    "name": "outdoor-pet"
  },
  "id": 3,
  "name": "Wolfy",
  "photoUrls": [
    "../../assets/images/wolf.png"
  ],
  "status": "available",
  "tags": [
    {
      "id": 3,
      "name": "wolf"
    }
  ]
}
```

--------------------------------
[json-schema]

```json
{ 
    "_id" : NumberLong(0), 
    "category" : {
        "_id" : NumberLong(0), 
        "name" : "string"
    }, 
    "name" : "doggie", 
    "photoUrls" : [
        "string"
    ], 
    "tags" : [
        {
            "_id" : NumberLong(0), 
            "name" : "string"
        }
    ], 
    "status" : "AVAILABLE", 
    "_class" : "org.openapitools.model.Pet"
}
```

```json
{
  "category": {
    "id": 0,
    "name": "string"
  },
  "id": 0,
  "name": "Test doggie",
  "photoUrls": [
    "string"
  ],
  "status": "pending",
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ]
}
```

---

[wolf-for-manual-mongodb-insert]

```json
{ 
    "_id" : NumberLong(99), 
    "category" : {
        "_id" : NumberLong(99), 
        "name" : "string"
    }, 
    "name" : "Wolfy", 
    "photoUrls" : [
        "string"
    ], 
    "tags" : [
        {
            "_id" : NumberLong(99), 
            "name" : "string"
        }
    ], 
    "status" : "AVAILABLE", 
    "_class" : "org.openapitools.model.Pet"
}
```
--------------------------------

Some OpenAPI Swagger links:

http://localhost:8080/swagger-ui/index.html?urls.primaryName=SWAGGER-springdoc-default-generated-openapi-spec
http://localhost:8080/v3/api-docs/swagger-config
http://localhost:8080/swagger-openapi-specs/org-springdoc-openapi-maven-plugin-generated-swagger-openapi-spec.json
http://localhost:8080/swagger-openapi-specs/openapi-spec-with-nullables.json
http://localhost:8080/JAX-RS-io-swagger-core-v3-swagger-maven-plugin-generated-openapi-spec.json
http://localhost:8080/v3/api-docs/

--------------------------------
--------------------------------
--------------------------------


