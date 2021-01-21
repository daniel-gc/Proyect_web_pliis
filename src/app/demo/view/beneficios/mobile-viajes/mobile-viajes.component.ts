import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-mobile-viajes',
  templateUrl: './mobile-viajes.component.html',
  styleUrls: ['./mobile-viajes.component.css']
})
export class MobileViajesComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Beneficios/Viajes', routerLink: ['/beneficios/mobileViajes']}
        ]);
    }

  ngOnInit() {
  }

}
