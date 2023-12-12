import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Data } from './app.component';
import {Observable} from "rxjs";

@Injectable(
  {
    providedIn: "root"
  }
)


export class AppService{
  title = 'fronted-jwt';
  email = '';
  password = '';
  private header = new HttpHeaders();


  constructor(private httpClient: HttpClient)
  {
  }

  loginApi(payload: any) {
    return this.httpClient.post('http://localhost:8080/api/login', payload)
  }

  getData(token: string) : Observable<Data[]> {
    this.header = this.header.set('Authorization', 'Bearer ' + token);
    console.log('test1');
    return this.httpClient.get<Data[]>('http://localhost:8080/api/books', {'headers': this.header})
  }
}
