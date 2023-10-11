import { Component, OnInit } from '@angular/core';
import { AnimalMovementService } from '../animal-movement.service';
import { Movement } from '../models/movement.model';
import { Farm } from '../models/farm.model';
import * as leaflet from 'leaflet';

@Component({
  selector: 'app-movement',
  templateUrl: './movement.component.html',
  styleUrls: ['./movement.component.css']
})
export class MovementComponent implements OnInit {
  movements: Movement[] = [];
  farm!: Farm;
  p: number = 1;
  selectedFarm: string = '';
  premiseIds: string[] = [];
  reasons: string[] = [];
  newMovementData: any = {};
  errorMessage: string = '';
  successMessage: string = '';
  private map!: leaflet.Map;
  showMap: boolean = true;

  constructor(private animalMovementsService: AnimalMovementService) { }

  ngOnInit(): void {
    this.getMovmentDetails();
    this.loadMap()
  }

  public loadMap() {
    this.map = leaflet.map('map').setView([39.8283, -98.5795], 4);
    leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);
  }

  public getMovmentDetails() {
    this.animalMovementsService.fetchMovements().subscribe(
      (data) => {
        this.movements = data
        this.movements.forEach((movement) => {
          if (!(movement.origin.premiseId in this.premiseIds)) {
            this.premiseIds.push(movement.origin.premiseId);
          }
          if (!(movement.destination.premiseId in this.premiseIds)) {
            this.premiseIds.push(movement.destination.premiseId);
          }
          if (!(movement.movementReason in this.reasons)) {
            this.reasons.push(movement.movementReason);
          }
        });
      },
      (error) => {
        console.error(error);
      }
    )
  }

  public getFarmDetails(premiseId: string) {
    this.selectedFarm = premiseId
    this.animalMovementsService.fetchFarm(premiseId).subscribe(
      (data) => {
        this.farm = data;
      },
      (error) => {
        console.log(error)
      }
    )
  }

  public generateMap(movement: Movement) {
    this.map.remove();
    this.loadMap();
    this.addMarkers(movement);
  }

  private addMarkers(movement: Movement): void {
    if(this.map){
      const sourceMarker = leaflet.marker([movement.origin.latitude, movement.origin.longitude],{icon: leaflet.icon({
        iconUrl: 'assets/origin-marker.svg',
        iconSize: [32, 32],
      })}).addTo(this.map).bindTooltip(movement.origin.premiseId+' : '+movement.origin.address+', '+movement.origin.city+ ', '+movement.origin.state+' '+movement.origin.postalCode, 
      {permanent: true, direction: 'right', opacity: 0.7});
    
      const destinationMarker = leaflet.marker([movement.destination.latitude, movement.destination.longitude], {icon: leaflet.icon({
        iconUrl: 'assets/destination-marker.svg',
        iconSize: [32, 32]
      })}).addTo(this.map).bindTooltip(movement.destination.premiseId+' : '+ movement.destination.address+', '+movement.destination.city+ ', '+movement.destination.state+' '+movement.destination.postalCode, 
      {permanent: true, direction: 'right', opacity: 0.7});
      
      const line = leaflet.polyline([
        [movement.origin.latitude, movement.origin.longitude],
        [movement.destination.latitude, movement.destination.longitude]], {
        color: 'red',
        opacity: 0.5
      }).addTo(this.map);
    }
  }


}
