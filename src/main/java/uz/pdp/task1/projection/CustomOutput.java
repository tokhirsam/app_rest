package uz.pdp.task1.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Client;
import uz.pdp.task1.entity.Currency;
import uz.pdp.task1.entity.Output;
import uz.pdp.task1.entity.WareHouse;
import java.sql.Timestamp;

@Projection(types = Output.class)
public class CustomOutput {
    Integer getId;
    Timestamp getDate;
    WareHouse getWareHouse;
    Client getClient;
    Currency getCurrency;
    String getFactureNumber;
    String getCode;
}
