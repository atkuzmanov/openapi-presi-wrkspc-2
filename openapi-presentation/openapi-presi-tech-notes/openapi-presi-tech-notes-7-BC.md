# Open API presentation notes 1

--------------------------------

`docker compose -p pets1 up `

--------------------------------
[backend]

```sh
openapi-generator generate -i openapi_openapitools-github_3.0.0.yaml -g spring -o spring-petstore/
```

--------------------------------
[backend]

```sh
mvn clean install

mvn spring-boot:run
```

--------------------------------
[frontend]

```sh
ng new angular-petstore
```

```sh
╰─$ ng new angular-petstore
? Do you want to enforce stricter type checking and stricter bundle budgets in the workspace?
  This setting helps improve maintainability and catch bugs ahead of time.
  For more information, see https://angular.io/strict Yes
? Would you like to add Angular routing? Yes
? Which stylesheet format would you like to use? CSS
```

```sh
### This command does not generate services.
# openapi-generator generate -i openapi_openapitools-github_3.0.0.yaml -g typescript-angular -o angular-petstore/src/app/api
```

```sh
### This command generates services.
ng-openapi-gen --input openapi_openapitools-github_3.0.0.yaml --output angular-petstore/src/app/api
```

```sh
cd angular-petstore
```

```sh
npm install --force

npm install --legacy-peer-deps

### ???
npm install --legacy-peer-deps --force

### Possible fixes for random problems:

# npm install --save @angular/material --force
# npm install --save @angular/material --legacy-peer-deps
# ng add @angular/material

# npm install -D tslib @types/node

# npm i uri-js -g
```

```sh
npm run build

ng build
```

```sh
ng serve
```

---

```sh
ng generate component petstore
```

---

```sh
ng test --main src/spec/openapivalidation.spec.ts
```

```sh
ng test --include src/app/petstore/petstore.component.spec.ts
```

```sh
ng test
```

```sh
npm install --save mocha

npm install --save supertest

npm install --save chai

npm install --save express-openapi-validate
```

```sh
npm run test_mocha
```

---

https://stackoverflow.com/questions/64037998/nullinjectorerror-r3injectorerrordynamictestmoduleapiservice-httpclient

```javascript
import { HttpClientModule, HttpClient } from '@angular/common/http';

    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [HttpClient]
    }).compileComponents();
```

---

```javascript
,
  "browser": {
    "fs": false,
    "os": false,
    "path": false
  }
``` 

---
[base-service.ts]

<https://loopback.io/doc/en/lb4/Building-frontend-angular-application.html>
<https://pguso.medium.com/generate-openapi-angular-client-8c9288e8bbd4>
<https://angular-schule.github.io/website-articles/blog/2018-04-swagger-codegen/README.html>

```javascript
private _rootUrl: string = 'http://localhost:8080/v1';
```

--------------------------------
[frontend]

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/pets1/pets1.component.ts]

```ts
import { Component, OnInit } from '@angular/core';
import {Pet} from '../api/models/pet'
import {PetsService} from '../api/services/pets.service'

@Component({
  selector: 'app-pets1',
  templateUrl: './pets1.component.html',
  styleUrls: ['./pets1.component.css']
})
export class Pets1Component implements OnInit {

  pets: Pet[];

  constructor(private petsService: PetsService) { }

  ngOnInit(): void {
    this.getPets();
  }

 
  getPets(): void {
    let page: number = 100;
    this.petsService.listPets().subscribe(pets =>(this.pets = pets));
  }


}
```
---

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/pets1/pets1.component.html]

```html
<p>pets1 works!</p>

<ul class="pets">
    <li *ngFor="let pet of pets">
      <span class="badge"></span> - {{pet.name}}
    </li>
</ul>

```

---

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/app.module.ts]

```ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { Pets1Component } from './pets1/pets1.component';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    Pets1Component
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

---

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/app.component.ts]

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular2';
}
```

---

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/app-routing.module.ts]

```ts
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { Pets1Component } from './pets1/pets1.component';
import { OnInit } from '@angular/core';
import { Router, Route } from "@angular/router";

const routes: Routes = [
    // { path: '', component: AppComponent },
    { path: 'pets', component: Pets1Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule implements OnInit { 
    constructor(private router: Router) {}

    ngOnInit() {
    console.log('>>> configured routes: ', this.router.config);
    }
}
```

---

[/Users/kuzmanov/Downloads/temp1/angular2/src/main.ts]

```ts
import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

```

---

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/app.component.html]

```html

<app-pets1></app-pets1>

```

---

[/Users/kuzmanov/Downloads/temp1/angular2/src/app/api/base-service.ts]

```ts
/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfiguration } from './api-configuration';

/**
 * Base class for services
 */
@Injectable()
export class BaseService {
  constructor(
    protected config: ApiConfiguration,
    protected http: HttpClient
  ) {
  }

  private _rootUrl: string = 'http://localhost:8080/v1';

  /**
   * Returns the root url for all operations in this service. If not set directly in this
   * service, will fallback to `ApiConfiguration.rootUrl`.
   */
  get rootUrl(): string {
    return this._rootUrl || this.config.rootUrl;
  }

  /**
   * Sets the root URL for API operations in this service.
   */
  set rootUrl(rootUrl: string) {
    this._rootUrl = rootUrl;
  }
}
```

--------------------------------
[frontend-validation-1]

https://itnext.io/openapi-swagger-specifications-that-write-your-tests-for-you-sort-of-82276a491c68
https://www.npmjs.com/package/express-openapi-validate

https://morioh.com/p/0e267ac4847d
https://www.npmjs.com/package/express-openapi-validator

--------------------------------
[references]
[image-references]

https://medium.com/better-practices/api-first-software-development-for-modern-organizations-fdbfba9a66d3

https://xkcd.com/1172/

https://openclipart.org/detail/193532/contract
https://openclipart.org/detail/192629/gear-tools
https://openclipart.org/detail/202774/gear
https://openclipart.org/detail/202775/gear-3d
https://openclipart.org/detail/215760/simple-gear

https://swagger.io/blog/api-design/design-first-or-code-first-api-development/

https://en.wikipedia.org/wiki/Design_by_contract


--------------------------------

`docker compose -p pets1 up `

```yml
    networks:
      - my-network-1

networks:
  my-network-1:
    name: my-network-1
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
  "name": "Scooby-Doo",
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
  "name": "Tom",
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
  "name": "White Fang",
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


--------------------------------
[errors-frontend]

```text
Access to XMLHttpRequest at 'http://localhost:8080/pet/findByTags?tags=dog%2Ccat%2Cwolf' from origin 'http://localhost:4200' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
```

```sh
npm install cors
```

---

`Module not found: Error: Can't resolve 'fs' in`

<https://stackoverflow.com/questions/57161839/module-not-found-error-cant-resolve-fs-in>

```text
The error is because of angular-cli does not support modules in node like "fs" and "path". (Issue)

Add the following to the "package.json" file.

"browser": {
  "fs": false,
  "path": false,
  "os": false
}
I hope this helps someone.

Thanks.

Share
Edit
Follow
Flag
edited Jun 3 at 14:45

Cool or Fool - SRS
30522 gold badges33 silver badges1515 bronze badges
answered May 25 '20 at 22:15

Anjana Silva
5,27533 gold badges4141 silver badges45
```

--------------------------------
[errors-backend]

swagger error:

```text
AbstractSerializableParameter : Illegal DefaultValue null for parameter type integer java.lang.NumberFormatException: For input string: ""
```

<https://www.dariawan.com/tutorials/rest/numberformatexception-for-input-string-in-swagger/>
<https://stackoverflow.com/questions/56770356/apiparam-type-not-supported-to-other-than-string>

---

Spring Data mongo case insensitive like query

<https://stackoverflow.com/questions/41746370/spring-data-mongo-case-insensitive-like-query>
<https://stackoverflow.com/questions/44340630/can-we-use-ignorecase-with-whitespace-in-mongorepository>

---

`TypeError: h.count is not a function`

<https://github.com/swagger-api/swagger-ui/issues/3033>

<https://github.com/swagger-api/swagger-ui/issues/3718>

<https://stackoverflow.com/questions/45515912/swagger-is-could-not-render-this-component-see-the-console-an-error-messa>

<https://mvnrepository.com/artifact/io.springfox/springfox-swagger2>
<https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui>

<https://github.com/springfox/springfox/issues/3554>

---

`@RequestPart value is null application/x-www-form-urlencoded`

<https://stackoverflow.com/questions/63885130/post-request-with-content-type-application-x-www-form-urlencoded-not-working-in>

<https://github.com/OpenAPITools/openapi-generator/issues/7794>

---

`spring-boot-starter-data-mongodb Map enum to String in the entity`

<https://stackoverflow.com/questions/64367755/spring-boot-starter-data-mongodb-map-enum-to-string-in-the-entity>

--------------------------------
--------------------------------
--------------------------------



