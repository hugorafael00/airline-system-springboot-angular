import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { inject } from '@angular/core';
import { AuthService } from '../auth/auth.service';

export interface Data {
    flightReservationNumber: string;
    source: string;
    destination: string;
}

@Component({
    selector: 'app-myflight',
    standalone: true,
    imports: [
        CommonModule,
    ],
    templateUrl: './myflight.component.html',
    styleUrl: './myflight.component.css'
})
export class MyflightComponent {
    authService = inject(AuthService);

    myflight: Data[] = [];

    ngOnInit() {
        console.log("myflight")
        return this.authService.myFlights().subscribe((data: any) => {
            console.log(data);

            for(let i = 0; i < data.length; i++) {
                const flightData: Data = {
                    flightReservationNumber: data[i].reservationNumber,
                    source: data[i].flightDto.source,
                    destination: data[i].flightDto.destination
                }
                console.log(flightData);
                this.myflight.push(flightData);
            }
        });
    }
}
