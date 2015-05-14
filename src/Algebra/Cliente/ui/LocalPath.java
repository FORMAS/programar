package Algebra.Cliente.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Algebra.Cliente.Tela_Principal;

public class LocalPath {
	
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN | SWT.ICON_QUESTION);
		//shell.setBounds(display.getBounds().width/2-145, display.getBounds().height/2 - 50, 290, 100);
		shell.setBounds(display.getBounds().width/2-145, display.getBounds().height/2 - 50, 340, 100);
		shell.setText("Escolha um local");
		final Text localText = new Text(shell, SWT.BORDER);
		int height = 25, width = 220;
		int y = shell.getSize().y/2 - 3*height/2;
		localText.setBounds(5, y, width, height);
		localText.setText("local...");
		localText.setForeground(new Color(display, 110, 110, 110));
		localText.setSize(width, height);

		Button button = new Button(shell, SWT.PUSH);
		button.setText("Procurar");
		//button.setBounds(width + 10, y, 50, height);
		button.setBounds(width + 10, y, 100, height);
		
		final Button exit = new Button(shell, SWT.PUSH);
		exit.setText("OK");
		//exit.setBounds(width + 10, y + height + 10, 50, height);
		exit.setBounds(width + 10, y + height + 10, 100, height);
		exit.setEnabled(false);
		exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Tela_Principal.main();
				shell.close();
				display.dispose();
			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dialog = new DirectoryDialog (shell);
				dialog.setMessage("Escolha um local para salvar a base de dados");
				String platform = SWT.getPlatform();
				dialog.setFilterPath (platform.equals("win32") || platform.equals("wpf") ? "C:\\" : "/");
				String local = dialog.open ();
				Tela_Principal.local = local != null? local : "";
				localText.setText(Tela_Principal.local);
				if(localText != null)
					if (!(localText.equals("local...")) || !(localText.equals("")))
						exit.setEnabled(true);
			}
		});
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		
	}
}
