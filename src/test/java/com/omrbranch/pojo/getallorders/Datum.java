package com.omrbranch.pojo.getallorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datum {
    public int id;
    public String order_no;
    public String amount;
    public boolean wallet_show;
    public String wallet_amount;
    public String remaining_amount;
    public String duration;
}
