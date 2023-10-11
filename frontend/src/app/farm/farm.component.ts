import { Component, OnInit } from '@angular/core';
import { Farm } from '../models/farm.model';
import { AnimalMovementService } from '../animal-movement.service';

@Component({
  selector: 'app-farm',
  templateUrl: './farm.component.html',
  styleUrls: ['./farm.component.css']
})
export class FarmComponent implements OnInit{

  farms: Farm[] = [];
  p: number = 1;

  constructor(private animalMovementsService: AnimalMovementService) {}

  ngOnInit(): void {
    this.animalMovementsService.fetchFarms().subscribe(
      (data) => {
        this.farms = data;
      },
      (error) => {
        console.log(error);
      }
    )
  }
  
}
