import java.util.ArrayList;
import java.util.List;
class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Товар добавлен в корзину: " + product.getName());
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void clearCart() {
        products.clear();
    }
}
class Order {
    private final ShoppingCart cart;
    private final String customerName;
    private final String address;
    private boolean isPaid;

    public Order(ShoppingCart cart, String customerName, String address) {
        this.cart = cart;
        this.customerName = customerName;
        this.address = address;
        this.isPaid = false;
    }

    public void pay(double amount) {
        double totalPrice = cart.getTotalPrice();
        if (amount >= totalPrice) {
            isPaid = true;
            System.out.println("Оплата прошла успешно. Сдача: " + (amount - totalPrice));
        } else {
            System.out.println("Недостаточно средств для оплаты. Оплата отклонена.");
        }
    }

    public boolean isPaid() {
        return isPaid;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }
}

class OrderProcessor {
    public void processOrder(Order order) {
        if (order.isPaid()) {
            System.out.println("Заказ обрабатывается для клиента: " + order.getCustomerName());
            System.out.println("Адрес доставки: " + order.getAddress());
            System.out.println("Заказ отправлен.");
        } else {
            System.out.println("Невозможно обработать заказ. Оплата не подтверждена.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Ноутбук", 1000.00));
        cart.addProduct(new Product("Мышка", 20.00));

        System.out.println("Общая стоимость: " + cart.getTotalPrice());

        Order order = new Order(cart, "Иван Иванов", "г. Москва, ул. Пушкина, д. 10");

        order.pay(1020.00);

        OrderProcessor processor = new OrderProcessor();
        processor.processOrder(order);
    }
}
