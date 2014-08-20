package by.skakun.carrentalsystem.entity;

import by.skakun.carrentalsystem.exception.CarException;
import java.util.Objects;
import org.apache.log4j.Logger;

public class Car extends Entity {

    private static final Logger LOG = Logger.getLogger(Car.class);

    private String carname;
    private int price;
    private String image;
    private int active;

    public Car() {
    }

    public Car(int id, String carname, int price, String image, int active) {
        super(id);
        try {
            setCarname(carname);
            setPrice(price);
            setImage(image);
            setActive(active);
        } catch (CarException ex) {
            LOG.info("CarException while creating Car:" + ex);
        }
    }

    public Car(String carname, int price, String image, int active) {
        super();
        this.carname = carname;
        this.price = price;
        this.image = image;
        this.active = active;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) throws CarException {
        if (!carname.isEmpty()) {
            this.carname = carname;
        } else {
            throw new CarException("Car name can't be null");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws CarException {
        if (price > 0) {
            this.price = price;
        } else {
            throw new CarException("Price must be greater than 0");
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) throws CarException {
        if (!image.isEmpty()) {
            this.image = image;
        } else {
            throw new CarException("Car image can't be null");
        }
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) throws CarException {
        if (active >= 0) {
            this.active = active;
        } else {
            throw new CarException("Wrong information for field 'active'");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.carname);
        hash = 53 * hash + this.price;
        hash = 53 * hash + Objects.hashCode(this.image);
        hash = 53 * hash + this.active;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.carname, other.carname)) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Car{" + "carname=" + carname + ", price="
                + price + ", image address=" + image + ", active=" + active + '}';
    }

}
