import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable(
  {
    providedIn: "root"
  }
)


export class AppService{
  title = 'fronted-jwt';
  email = '';
  password = ``;


  constructor(private httpClient: HttpClient)
  {
  }

  loginApi(payload: any) {
    return this.httpClient.post('http://localhost:8080/api/login', payload)

  }
}
