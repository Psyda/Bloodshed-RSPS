package com.client;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author _Hassan <https://www.rune-server.ee/members/_hassan/> Created: 16 Apr
 *         2017 ~ client
 *
 */

public class ClientUpdater {

	/*public boolean checkVersion() {
		if (getVersion() > Configuration.CLIENT_VER) {
			showOptions();
			return true;
		}
		return false;

	}*/

	private final File downloadClient() throws IOException {
		File jar = new File(Configuration.CLIENT_LOCATION);

		if (!jar.exists())
			jar.createNewFile();

		try (OutputStream stream = new FileOutputStream(jar)) {

			URLConnection link = new URL(Configuration.CLIENT_LINK).openConnection();

			try (InputStream in = link.getInputStream()) {

				long downloaded = 0;
				long size = link.getContentLength();

				byte[] b = new byte[1024];

				int pct, length;

				while ((length = in.read(b, 0, b.length)) > -1) {

					stream.write(b, 0, length);
					downloaded += length;

					pct = (int) ((downloaded * 100) / size);

					Client.getClient().drawLoadingText(pct, Configuration.CLIENT_NAME + " is updating ("
							+ (double) Math.round((downloaded / 1024 / 1000)) + " mb " + pct + "%)");

				}

			}
		}
		Desktop.getDesktop().open(jar);
		System.exit(0);
		return jar;
	}

	/*final double getVersion() {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL(Configuration.VERSION_TXT).openConnection().getInputStream()))) {
			return Double.valueOf(reader.readLine().replaceAll("[^\\d.]", ""));
		} catch (IOException e) {
			e.printStackTrace();
			drawText("Something went wrong with the update. Try again or report it to a staff member for further help.",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return -1;
		}
	}*/

	void drawText(String text, int type) {
		JOptionPane.showMessageDialog(null, text, Configuration.CLIENT_NAME + " update", type);
	}

	void showOptions() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JTextArea text = new JTextArea(7, 5);
		JScrollPane scrollPane = new JScrollPane(text);
		text.setText("A client update is available (ver: "
				+ new DecimalFormat("##.#").format((Configuration.CLIENT_VER + 0.1))
				+ "). You must update in order to play. \n\nEvery client update will come with new features, improvements and fixes. Please update your client to keep up with the game."
				+ "\n\nWould you like to update now? ");
		text.setFont(new Font("Times New Roman", 0, 18));
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setCaretPosition(0);
		text.setEditable(false);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		int op = JOptionPane.showConfirmDialog(null, scrollPane, Configuration.CLIENT_NAME + " ~ UPDATE AVAILABLE!",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (op == 0)
			try {
				downloadClient();
			} catch (IOException e) {
				drawText("Something went wrong, try again.", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
				e.printStackTrace();
			}
		else
			System.exit(0);
	}

	private static ClientUpdater INSTANCE;

	public static ClientUpdater getClientUpdater() {
		if (INSTANCE == null)
			INSTANCE = new ClientUpdater();
		return INSTANCE;
	}

}
