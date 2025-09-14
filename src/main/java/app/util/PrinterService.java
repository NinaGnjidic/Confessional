package main.java.app.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPos.CharacterCodeTable;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.PrinterOutputStream;

import main.java.app.EnvironmentVariables;
import main.java.app.model.Detail;

public class PrinterService {
	private static final String ICON_IMAGE_PATH = "/images/icons/cross.png";

	public static void printConfessional(List<Detail> sins, String content, int points, int rank, float coins) throws IOException {
		PrintService printerService = findPrinter();

		EscPos escpos = new EscPos(new PrinterOutputStream(printerService));
		escpos.setCharacterCodeTable(CharacterCodeTable.CP852_Latin2);

		Style center = new Style().setJustification(EscPosConst.Justification.Center);
		Style left = new Style().setJustification(EscPosConst.Justification.Left_Default);
		Style boldCenter = new Style(center).setBold(true);
		Style bigNumbers = new Style(center).setBold(true).setFontSize(Style.FontSize._4, Style.FontSize._4);

		BufferedImage cross = ImageIO.read(PrinterService.class.getResource(ICON_IMAGE_PATH));

		int maxWidth = 200;
		int maxHeight = 200;
		double scale = Math.min((double) maxWidth / cross.getWidth(), (double) maxHeight / cross.getHeight());

		int newWidth = (int) (cross.getWidth() * scale);
		int newHeight = (int) (cross.getHeight() * scale);

		BufferedImage scaledCross = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = scaledCross.createGraphics();
		g.drawImage(cross, 0, 0, newWidth, newHeight, null);
		g.dispose();

		CoffeeImageImpl coffeeImage = new CoffeeImageImpl(scaledCross);
		EscPosImage escposImage = new EscPosImage(coffeeImage, new BitonalThreshold());

		RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
		imageWrapper.setJustification(EscPosConst.Justification.Center);

		escpos.writeLF(center, ""); // optional spacing line
		escpos.write(imageWrapper, escposImage);
		escpos.feed(1);
		escpos.writeLF(boldCenter, EnvironmentVariables.PRINT_TITLE);
		escpos.feed(1);
		escpos.writeLF(center, "________________________________________________");
		escpos.feed(1);
		
		if(sins.isEmpty()) {
			escpos.writeLF(center, EnvironmentVariables.PRINT_NO_SINS);
		} else {
			for(Detail sin : sins) {
				escpos.writeLF(center, sin.getName());
			}
		}
		escpos.writeLF(center, "________________________________________________");
		escpos.feed(1);

		escpos.writeLF(left, content);
		escpos.writeLF(center, "________________________________________________");
		escpos.feed(1);

		escpos.writeLF(center, EnvironmentVariables.PRINT_PRAYERS);
		escpos.feed(1);
		escpos.writeLF(bigNumbers, points + "");
		if(rank > 0 && rank <= 5) {
			escpos.feed(1);
			escpos.writeLF(center, EnvironmentVariables.PRINT_RANKING + rank);
		}
		escpos.writeLF(center, "________________________________________________");
		escpos.feed(1);
		
		escpos.writeLF(center, EnvironmentVariables.PRINT_COINS + coins);

		escpos.feed(6);
		escpos.cut(EscPos.CutMode.FULL);

		escpos.close();

		System.out.println("Sent to printer!");
	}
	
	private static PrintService findPrinter() {
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
		}

		return selectedPrinter;
	}
}
