package com.axelor.apps.gst.service.generator;

import com.axelor.apps.account.db.Invoice;
import com.axelor.apps.gst.service.GstInvoiceService;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.supplychain.service.invoice.generator.InvoiceGeneratorSupplyChain;
import com.axelor.exception.AxelorException;
import com.google.inject.Inject;

public class InvoiceGeneratorGst extends InvoiceGeneratorSupplyChain {

  @Inject private GstInvoiceService service;

  protected InvoiceGeneratorGst(SaleOrder saleOrder, boolean isRefund) throws AxelorException {
    super(saleOrder, isRefund);
  }

  @Override
  public Invoice generate() throws AxelorException {
    return null;
  }

  @Override
  protected Invoice createInvoiceHeader() throws AxelorException {

    Invoice invoice = super.createInvoiceHeader();

    System.out.println(invoice.getInvoiceLineList());
    invoice.setNetCgst(service.getAmounts(invoice.getInvoiceLineList(), "cgst"));
    invoice.setNetIgst(service.getAmounts(invoice.getInvoiceLineList(), "igst"));
    invoice.setNetSgst(service.getAmounts(invoice.getInvoiceLineList(), "sgst"));

    return invoice;
  }
}
