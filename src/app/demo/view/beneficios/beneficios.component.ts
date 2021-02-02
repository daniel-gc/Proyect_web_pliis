import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';

@Component({
  selector: 'app-llamadas',
  templateUrl: './beneficios.component.html',
  styleUrls: ['./beneficios.component.css']
})
export class BeneficiosComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Beneficios', routerLink: ['/beneficios']}
        ]);
    }

  ngOnInit() {
  }

}
