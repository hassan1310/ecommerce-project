export class Product {

   
     constructor(
        public sku : string,
        public description: string,
        public name: string,
        public unitPrice: number,
        public imageUrl: string,
        public active : boolean,
        public unitsInSocket:number,
        public dateCreated:Date,
        public lastUpdated:Date){}
}

