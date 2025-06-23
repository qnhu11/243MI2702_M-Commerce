package models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProduct implements Serializable {
    ArrayList<Product> products;

    public ListProduct() {
        products=new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void generate_sample_product_dataset()
    {
        products.add(new Product(1, "P001", "Coca Cola", 10.0, "https://minhcaumart.vn/media/com_eshop/products/8935049501404.webp"));
        products.add(new Product(2, "P002", "Pepsi", 9.5, "https://bizweb.dktcdn.net/100/514/431/products/nuoc-ngot-pepsi-cola-lon-320ml-202403091730333958.jpg?v=1716432062970"));
        products.add(new Product(3, "P003", "7Up", 8.0, "https://product.hstatic.net/1000301274/product/_10100996__7up_320ml_sleek_lon_0366766c074a4b538595ed8d91dc6b0d_1024x1024.png"));
        products.add(new Product(4, "P004", "Fanta", 8.5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSa4gbo0mYS2GlhilhETR8SBfVA8cqQg1K-vw&s"));
        products.add(new Product(5, "P005", "Sprite", 9.0, "https://product.hstatic.net/200000077081/product/e18_adef87e7cbf64567bb9d71abc0b82c94.jpg"));
        products.add(new Product(6, "P006", "Red Bull", 15.0, "https://shop.annam-gourmet.com/pub/media/catalog/product/cache/ee0af4cad0f3673c5271df64bd520339/i/t/item_F105092_9609.png"));
        products.add(new Product(7, "P007", "Monster Energy", 14.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGCXeUWeo0kmWx4A-hbLtI-hCcZysQX6ef0g&s"));
        products.add(new Product(8, "P008", "Lavie Water", 5.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(9, "P009", "Aquafina", 5.5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(10, "P010", "Nestle Water", 6.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(11, "P011", "Lipton Ice Tea", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(12, "P012", "Minute Maid Juice", 13.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(13, "P013", "Heineken Beer", 20.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(14, "P014", "Tiger Beer", 19.5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(15, "P015", "Budweiser", 21.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQax2khtICxdDREb8UGSZWrSmG3buFh67q3DQ&s"));
        products.add(new Product(16, "P016", "Cappy Juice", 11.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWT0Kc4wrmtlFS7C71fZG8LdlgtWH4pg-rWg&s"));
        products.add(new Product(17, "P017", "Vita Lemon Tea", 10.5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWT0Kc4wrmtlFS7C71fZG8LdlgtWH4pg-rWg&s"));
        products.add(new Product(18, "P018", "Peach Juice", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWT0Kc4wrmtlFS7C71fZG8LdlgtWH4pg-rWg&s"));
        products.add(new Product(19, "P019", "Apple Juice", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWT0Kc4wrmtlFS7C71fZG8LdlgtWH4pg-rWg&s"));
        products.add(new Product(20, "P020", "Orange Juice", 12.5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWT0Kc4wrmtlFS7C71fZG8LdlgtWH4pg-rWg&s"));
    }

    public boolean isExist(Product p)
    {
        for (Product pro:products)
        {
            if (pro.getId()==p.getId() ||
                    pro.getProductName()==p.getProductName() ||
                    pro.getProductCode()==p.getProductCode()
            )
                return true;
        }
        return false;
    }

    public void addProducts(Product p) {
        products.add(p);
    }
}
