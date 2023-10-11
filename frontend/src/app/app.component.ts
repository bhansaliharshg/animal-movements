import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Animal Movment';
  isMomentsActive: boolean = true;
  isFarmsActive: boolean = false;

  constructor(private router: Router) { }

}
