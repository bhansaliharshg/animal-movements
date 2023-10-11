import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movement } from './models/movement.model';
import { Farm } from './models/farm.model';

@Injectable({
  providedIn: 'root'
})
export class AnimalMovementService {

  private apiURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  fetchMovements() : Observable<any> {
    console.log(this.apiURL);
    let headers = new HttpHeaders();
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Content-Type', 'application/json');
    return this.http.get<Movement[]>(this.apiURL+'/movements', {
      headers: headers
    });
  }

  fetchFarms(): Observable<any> {
    console.log('Fetching farms');
    let headers = new HttpHeaders();
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Content-Type', 'application/json');
    return this.http.get<Farm[]>(this.apiURL+'/farms', {
      headers: headers
    });
  }

  fetchFarm(premiseId: string): Observable<any> {
    console.log('Fetching farm with PremiseId '+premiseId);
    let headers = new HttpHeaders();
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Content-Type', 'application/json');
    return this.http.get<Farm>(this.apiURL+'/farm/'+premiseId, {
      headers: headers
    });
  }

}
