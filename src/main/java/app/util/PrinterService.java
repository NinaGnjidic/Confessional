package main.java.app.util;

import java.io.UnsupportedEncodingException;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import main.java.app.EnvironmentVariables;

public class PrinterService {

    private static final byte[] CUT_BYTES = new byte[] { 0x1D, 'V', 0 };     // full cut
    private static final byte[] FEED_6_LINES_BYTES = new byte[] { 0x1B, 'd', 6 }; // feed 6 lines
    private static final byte[] SELECT_CP852_BYTES = new byte[] { 0x1B, 't', 18 }; // ESC t 18 = CP852 (Epson) TODO: env

    private PrinterService() { }

    public static void print(String escposData) throws UnsupportedEncodingException, PrintException {
        String printerName = EnvironmentVariables.PRINTER;

        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService selectedPrinter = null;

        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                selectedPrinter = service;
                break;
            }
        }

        if (selectedPrinter == null) {
            System.err.println("Printer not found!");
            return;
        }

        byte[] textBytes = escposData.getBytes(EnvironmentVariables.PRINTER_ENCODING);

        byte[] data = new byte[SELECT_CP852_BYTES.length + textBytes.length + FEED_6_LINES_BYTES.length + CUT_BYTES.length];

        int pos = 0;
        System.arraycopy(SELECT_CP852_BYTES, 0, data, pos, SELECT_CP852_BYTES.length);
        pos += SELECT_CP852_BYTES.length;

        System.arraycopy(textBytes, 0, data, pos, textBytes.length);
        pos += textBytes.length;

        System.arraycopy(FEED_6_LINES_BYTES, 0, data, pos, FEED_6_LINES_BYTES.length);
        pos += FEED_6_LINES_BYTES.length;

        System.arraycopy(CUT_BYTES, 0, data, pos, CUT_BYTES.length);

        DocPrintJob printJob = selectedPrinter.createPrintJob();
        try {
			printJob.print(new SimpleDoc(data, DocFlavor.BYTE_ARRAY.AUTOSENSE, null), null);
		} catch (PrintException e) {
			e.printStackTrace();
			return;
		}

        System.out.println("Sent to printer!");
    }
}
