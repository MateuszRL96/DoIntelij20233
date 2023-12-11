import {Component, OnInit} from '@angular/core';
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'fronted-jwt';
  email = '';
  password = ``;

  ngOnInit() {

  }

  constructor(private AppService: AppService) {
  }

  login() {
    console.log("email" + this.email);

    let payload = '{\n' +
      ' "email":"root",\n' +
      ' "password":"1234",\n' +
      '}';

    this.AppService.loginApi(payload).subscribe((res:any) => {
      console.log(res);
    });


  }
}
