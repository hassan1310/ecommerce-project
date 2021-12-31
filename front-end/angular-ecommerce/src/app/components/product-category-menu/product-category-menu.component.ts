import { Component, OnInit } from '@angular/core';
import { ProductCategory } from 'src/app/common/product-category';
import { ProductCategoryService } from 'src/app/services/product-category.service';

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css']
})
export class ProductCategoryMenuComponent implements OnInit {

  productCategories :ProductCategory [];
  constructor(private productCategoryService : ProductCategoryService) { }

  ngOnInit(): void {

    this.listCategories();
  }

  listCategories () {
    this.productCategoryService.getProductCategoryList().subscribe(
      data =>{
        console.log("This is the data "+JSON.stringify(data));
        this.productCategories=data;
        
      
    });
  }
}
