package connectors;

import java.util.ArrayList;

import models.ListProduct;
import models.Product;

public class ProductConnector {
    ListProduct listProduct;
    public ProductConnector()
    {
        listProduct=new ListProduct();
        listProduct.generate_sample_product_dataset();
    }
    public ArrayList<Product> get_all_products()
    {
        if (listProduct==null)
        {
            listProduct=new ListProduct();
            listProduct.generate_sample_product_dataset();
        }
        return listProduct.getProducts();
    }

    public boolean isExit(Product p)
    {
        return listProduct.isExist(p);
    }
    public void addProducts(Product p)
    {
        listProduct.addProducts(p);
    }
}
