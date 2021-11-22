package com.axelor.apps.gst.service;

import com.axelor.apps.account.db.InvoiceLine;
import java.math.BigDecimal;
import java.util.List;

public interface GstInvoiceService {

  public BigDecimal getAmounts(List<InvoiceLine> il, String str);
}
