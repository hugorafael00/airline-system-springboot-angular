import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { inject } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators, FormsModule } from '@angular/forms';


@Component({
  selector: 'app-flights',
  standalone: true,
  imports: [ CommonModule, FormsModule, ReactiveFormsModule ],
  templateUrl: './flights.component.html',
  styleUrl: './flights.component.css'
})
export class FlightsComponent {
  authService = inject(AuthService);
  data: any[] = [];

  ngOnInit() {

    return this.authService.getFlights().subscribe((data: any) => {
      console.log(data);
      this.data = data;}
    );
  }


  buy(flightNumber: string) {
    console.log(flightNumber);
    this.authService.buyFlight(flightNumber)
    .subscribe((data: any) => {
      console.log(data);
    })
  }

}
