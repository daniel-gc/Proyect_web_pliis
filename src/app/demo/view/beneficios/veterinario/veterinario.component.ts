import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-veterinario',
  templateUrl: './veterinario.component.html',
  styleUrls: ['./veterinario.component.css']
})
export class VeterinarioComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Beneficios/Red Veterinaria', routerLink: ['/beneficios/redVeterinaria']}
        ]);
    }

  ngOnInit() {
  }

}
