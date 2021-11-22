package com.axelor.apps.gst.service;

import com.axelor.apps.account.db.InvoiceLine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GstInvoiceServiceImplementation implements GstInvoiceService {

  @Override
  public BigDecimal getAmounts(List<InvoiceLine> il, String str) {
    List<BigDecimal> list = new ArrayList<BigDecimal>();

    if (str.equalsIgnoreCase("netAmount")) {
      for (InvoiceLine item : il) list.add(item.getExTaxTotal());
    } else if (str.equalsIgnoreCase("igst")) {
      for (InvoiceLine item : il) list.add(item.getIgst());
    } else if (str.equalsIgnoreCase("sgst")) {
      for (InvoiceLine item : il) list.add(item.getSgst());
    } else if (str.equalsIgnoreCase("cgst")) {
      for (InvoiceLine item : il) list.add(item.getCgst());
    } else if (str.equalsIgnoreCase("grossAmount")) {
      for (InvoiceLine item : il) list.add(item.getInTaxPrice());
    }

    BigDecimal total = BigDecimal.valueOf(0);

    for (BigDecimal value : list) total = total.add(value);

    return total;
  }
}
