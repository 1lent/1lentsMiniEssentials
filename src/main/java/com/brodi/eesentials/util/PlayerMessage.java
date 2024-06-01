package com.brodi.eesentials.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class PlayerMessage {

    public static void send(Player player, String message) {
        player.sendMessage(formatColors(message));
    }

    public static TextComponent formatColors(String message) {
        TextComponent.Builder formattedMessage = Component.text();
        TextComponent.Builder currentComponent = Component.text();

        TextColor currentColor = NamedTextColor.WHITE;
        boolean obfuscated = false;
        boolean bold = false;
        boolean strikethrough = false;
        boolean underlined = false;
        boolean italic = false;

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            if (c == '&' && i + 1 < message.length()) {
                char nextChar = message.charAt(i + 1);
                if (isColorCode(nextChar) || isDecorationCode(nextChar)) {
                    formattedMessage.append(currentComponent.build());
                    
                    if (isDecorationCode(nextChar)) {
                        switch (Character.toLowerCase(nextChar)) {
                            case 'k': obfuscated = !obfuscated; break;
                            case 'l': bold = !bold; break;
                            case 'm': strikethrough = !strikethrough; break;
                            case 'n': underlined = !underlined; break;
                            case 'o': italic = !italic; break;
                            case 'r':
                                currentColor = NamedTextColor.WHITE;
                                obfuscated = false;
                                bold = false;
                                strikethrough = false;
                                underlined = false;
                                italic = false;
                                break;
                        }
                    } else {
                        TextColor color = getColorFromCode(nextChar);
                        if (color != null) {
                            currentColor = color;
                        }
                    }
                    i++;
                    currentComponent = Component.text().color(currentColor)
                            .decoration(TextDecoration.OBFUSCATED, obfuscated)
                            .decoration(TextDecoration.BOLD, bold)
                            .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                            .decoration(TextDecoration.UNDERLINED, underlined)
                            .decoration(TextDecoration.ITALIC, italic);
                } else {
                    currentComponent.append(Component.text(c));
                }
            } else if (c == '#' && i + 7 <= message.length()) {
                String hexCode = message.substring(i, i + 7);
                if (isValidHexCode(hexCode)) {
                    formattedMessage.append(currentComponent.build());

                    TextColor color = TextColor.fromHexString(hexCode);
                    if (color != null) {
                        currentColor = color;
                    }
                    i += 6;
                    currentComponent = Component.text().color(currentColor)
                            .decoration(TextDecoration.OBFUSCATED, obfuscated)
                            .decoration(TextDecoration.BOLD, bold)
                            .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                            .decoration(TextDecoration.UNDERLINED, underlined)
                            .decoration(TextDecoration.ITALIC, italic);
                } else {
                    currentComponent.append(Component.text(c));
                }
            } else {
                currentComponent.append(Component.text(String.valueOf(c))
                        .color(currentColor)
                        .decoration(TextDecoration.OBFUSCATED, obfuscated)
                        .decoration(TextDecoration.BOLD, bold)
                        .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                        .decoration(TextDecoration.UNDERLINED, underlined)
                        .decoration(TextDecoration.ITALIC, italic));
            }
        }

        formattedMessage.append(currentComponent.build());
        return formattedMessage.build();
    }

    private static boolean isColorCode(char c) {
        return "0123456789AaBbCcDdEeFf".indexOf(c) > -1;
    }

    private static boolean isDecorationCode(char c) {
        return "KkLlMmNnOoRr".indexOf(c) > -1;
    }

    private static TextColor getColorFromCode(char c) {
        switch (Character.toLowerCase(c)) {
            case '0': return NamedTextColor.BLACK;
            case '1': return NamedTextColor.DARK_BLUE;
            case '2': return NamedTextColor.DARK_GREEN;
            case '3': return NamedTextColor.DARK_AQUA;
            case '4': return NamedTextColor.DARK_RED;
            case '5': return NamedTextColor.DARK_PURPLE;
            case '6': return NamedTextColor.GOLD;
            case '7': return NamedTextColor.GRAY;
            case '8': return NamedTextColor.DARK_GRAY;
            case '9': return NamedTextColor.BLUE;
            case 'a': return NamedTextColor.GREEN;
            case 'b': return NamedTextColor.AQUA;
            case 'c': return NamedTextColor.RED;
            case 'd': return NamedTextColor.LIGHT_PURPLE;
            case 'e': return NamedTextColor.YELLOW;
            case 'f': return NamedTextColor.WHITE;
            default: return null;
        }
    }

    private static boolean isValidHexCode(String hexCode) {
        return hexCode.matches("#[A-Fa-f0-9]{6}");
    }
}
