
package com.mycompany.idan;

import com.mycompany.idan.config.AppContext;

public class Main {
    public static void main(String[] args) {
        // Hidupkan Spring + Hibernate sekali di awal
        AppContext.akunDao();
        Runtime.getRuntime().addShutdownHook(new Thread(AppContext::shutdown));

        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Login");

            frame.setDefaultCloseOperation(
                javax.swing.JFrame.EXIT_ON_CLOSE
            );

            frame.setContentPane(new LoginForm());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
