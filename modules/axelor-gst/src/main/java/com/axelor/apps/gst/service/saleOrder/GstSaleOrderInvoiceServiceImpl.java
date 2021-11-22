package com.axelor.apps.gst.service.saleOrder;

import com.axelor.apps.account.db.Invoice;
import com.axelor.apps.account.db.repo.InvoiceRepository;
import com.axelor.apps.account.service.invoice.InvoiceService;
import com.axelor.apps.account.service.invoice.generator.InvoiceGenerator;
import com.axelor.apps.base.service.app.AppBaseService;
import com.axelor.apps.businessproject.service.app.AppBusinessProjectService;
import com.axelor.apps.gst.service.generator.InvoiceGeneratorGst;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.repo.SaleOrderRepository;
import com.axelor.apps.sale.service.saleorder.SaleOrderLineService;
import com.axelor.apps.sale.service.saleorder.SaleOrderWorkflowServiceImpl;
import com.axelor.apps.stock.db.repo.StockMoveRepository;
import com.axelor.apps.supplychain.service.app.AppSupplychainService;
import com.axelor.exception.AxelorException;
import com.google.inject.Inject;

public class GstSaleOrderInvoiceServiceImpl extends GstSaleOrderInvoiceLineServiceImpl {

  @Inject
  public GstSaleOrderInvoiceServiceImpl(
      AppBaseService appBaseService,
      AppSupplychainService appSupplychainService,
      SaleOrderRepository saleOrderRepo,
      InvoiceRepository invoiceRepo,
      InvoiceService invoiceService,
      AppBusinessProjectService appBusinessProjectService,
      StockMoveRepository stockMoveRepository,
      SaleOrderLineService saleOrderLineService,
      SaleOrderWorkflowServiceImpl saleOrderWorkflowServiceImpl) {
    super(
        appBaseService,
        appSupplychainService,
        saleOrderRepo,
        invoiceRepo,
        invoiceService,
        appBusinessProjectService,
        stockMoveRepository,
        saleOrderLineService,
        saleOrderWorkflowServiceImpl);
  }

  @Override
  public InvoiceGenerator createInvoiceGenerator(SaleOrder saleOrder, boolean isRefund)
      throws AxelorException {

    return new InvoiceGeneratorGst(saleOrder, isRefund) {
      @Override
      public Invoice generate() throws AxelorException {
        Invoice invoice = super.createInvoiceHeader();
        return invoice;
      }
    };
  }
}
