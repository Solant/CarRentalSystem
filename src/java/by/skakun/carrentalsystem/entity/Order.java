package by.skakun.carrentalsystem.entity;

import by.skakun.carrentalsystem.exception.OrderException;
import java.sql.Date;
import java.util.Objects;

public class Order extends Entity {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Order.class);

    private int userId;
    private int carId;
    private int sumToPay;
    private boolean paid;
    private Damage damaged;
    private int returned;
    private int confirmed;
    private String refusalReason;
    private String carName;
    private int price;
    private int period;
    private Date date;
    private String passNum;
    private String clientSurname;

    public Order() {
    }

    public Order(int userId, int carId, int sumToPay, boolean paid, Damage damaged, int returned, int confirmed, String refusalReason) {
        this.userId = userId;
        this.carId = carId;
        this.sumToPay = sumToPay;
        this.paid = paid;
        this.damaged = damaged;
        this.returned = returned;
        this.confirmed = confirmed;
        this.refusalReason = refusalReason;
    }

    public Order(int userId, int carId, int sumToPay, int price, int period, Date date) {
        try {
            setUserId(userId);
            setCarId(carId);
            setSumToPay(sumToPay);
            setPeriod(period);
            setPrice(price);
            setDate(date);
        } catch (OrderException ex) {
            LOG.info("ClientException while creating Client:" + ex);
        }
    }

    public Order(int userId, int carId, int sumToPay, boolean paid, Damage damaged, int returned, int confirmed, int id, String refusalReason) {
        super(id);
        this.userId = userId;
        this.carId = carId;
        this.sumToPay = sumToPay;
        this.paid = paid;
        this.damaged = damaged;
        this.returned = returned;
        this.confirmed = confirmed;
        this.refusalReason = refusalReason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) throws OrderException {
        if (period > 0) {
            this.period = period;
        } else {
            throw new OrderException("Period of rent should be greater than 0");
        }
    }

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) throws OrderException {
        if (!passNum.isEmpty()) {
            this.passNum = passNum;
        } else {
            throw new OrderException("Passnum shouldn't be null");
        }
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) throws OrderException {
        if (!clientSurname.isEmpty()) {
            this.clientSurname = clientSurname;
        } else {
            throw new OrderException("Client Surname shouldn't be null");
        }
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) throws OrderException {
        if (!carName.isEmpty()) {
            this.carName = carName;
        } else {
            throw new OrderException("Car name  shouldn't be null");
        }

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws OrderException {
        if (price > 0) {
            this.price = price;
        } else {
            throw new OrderException("Price should be greater than 0");
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getSumToPay() {
        return sumToPay;
    }

    public void setSumToPay(int sumToPay) throws OrderException {
        if (sumToPay > 0) {
            this.sumToPay = sumToPay;
        } else {
            throw new OrderException("Sum to pay should be greater than 0");
        }
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Damage getDamaged() {
        return damaged;
    }

    public void setDamaged(Damage damaged) {
        this.damaged = damaged;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public String getRefusalReason() {
        return refusalReason;
    }

    public void setRefusalReason(String refusalReason) {
        this.refusalReason = refusalReason;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.userId;
        hash = 67 * hash + this.carId;
        hash = 67 * hash + this.sumToPay;
        hash = 67 * hash + (this.paid ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.damaged);
        hash = 67 * hash + Objects.hashCode(this.returned);
        hash = 67 * hash + Objects.hashCode(this.confirmed);
        hash = 67 * hash + Objects.hashCode(this.refusalReason);
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
        final Order other = (Order) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.carId != other.carId) {
            return false;
        }
        if (this.sumToPay != other.sumToPay) {
            return false;
        }
        if (this.paid != other.paid) {
            return false;
        }
        if (this.damaged != other.damaged) {
            return false;
        }
        if (this.returned != other.returned) {
            return false;
        }
        if (this.confirmed != other.confirmed) {
            return false;
        }
        if (!Objects.equals(this.refusalReason, other.refusalReason)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Application{" + "userId=" + userId + ", carId=" + carId + ", sumToPay=" + sumToPay + ", paid=" + paid + ", damaged=" + damaged + ", returned=" + returned + ", confirmed=" + confirmed + ", refusalReason=" + refusalReason + ", carName=" + carName + ", price=" + price + ", period=" + period + ", passNum=" + passNum + ", clientSurname=" + clientSurname + '}';
    }

}

enum Confirmation {

    YES, NO;
}

enum Damage {

    AO("побит вокруг"),
    BC("биохимическое повреждение"),
    BE("горел двигатель"),
    BI("горел салон"),
    BN("горел весь"),
    FR("удар в перед автомобиля"),
    MC("механическое повреждение"),
    NO("нет видимых повреждений"),
    RJ("отказ в починке"),
    RO("перевертыш"),
    SD("удар в бок"),
    ST("чего-то не хватает"),
    TP("разукомлектован"),
    UK("повреждена крыша"),
    UN("неухоженная"),
    VN("вандализм"),
    WA("автомобиль был затоплен");

    private final String description;

    Damage(String description) {
        this.description = description;
    }
}
