package com.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.PluginErrorPanel;
import net.runelite.client.util.AsyncBufferedImage;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.SwingUtil;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CompanionPetPanel extends PluginPanel {


    @Inject
    public CompanionPetPanel(
            JPanel sidePanel,
            JPanel titlePanel,
            JPanel petButtonsPanel,
            JPanel petSelectionTitlePanel,
            JPanel currentPetTitle,
            JPanel currentPetPanel,
            JLabel currentPetIcon,
            JPanel spacerPanel,
            JPanel spacerPanelTop,
            JPanel favPetButtonsPanel,
            JPanel spacerPanelFavPets,
            JPanel thrallPanel,
            JPanel spacerAndTextForThralls,
            JPanel thrallTitlePanel,
            JPanel spacerThrallTop)
    {
        this.sidePanel = sidePanel;
        this.titlePanel = titlePanel;
        this.petButtonsPanel = petButtonsPanel;
        this.petSelectionTitlePanel = petSelectionTitlePanel;
        this.currentPetTitle = currentPetTitle;
        this.currentPetPanel = currentPetPanel;
        this.currentPetIcon = currentPetIcon;
        this.spacerPanelBottom = spacerPanel;
        this.spacerPanelTop = spacerPanelTop;
        this.favPetButtonsPanel = favPetButtonsPanel;
        this.spacerPanelFavPets = spacerPanelFavPets;
        this.thrallPanel = thrallPanel;
        this.spacerAndTextForThralls = spacerAndTextForThralls;
        this.thrallTitlePanel = thrallTitlePanel;
        this.thrallSpacerTop = spacerThrallTop;
    }

    @Inject
    private CompanionPetPlugin plugin;

    @Inject
    private CompanionPetConfig config;

    @Inject
    private ItemManager itemManager;

    @Inject
    private ClientThread clientThread;

    @Inject
    private ConfigManager configManager;

    private final JPanel sidePanel;
    private final JPanel titlePanel;
    private final JPanel petButtonsPanel;
    private final JPanel petSelectionTitlePanel;
    private final JPanel spacerPanelFavPets;
    private final JPanel favPetButtonsPanel;
    private final JPanel spacerPanelBottom;
    private final JPanel spacerPanelTop;
    private final JPanel currentPetTitle;
    private final JPanel thrallPanel;
    private final JPanel currentPetPanel;
    private final JPanel spacerAndTextForThralls;
    private final JPanel thrallTitlePanel;
    private final JPanel thrallSpacerTop;
    private final JLabel currentPetIcon;

    private static final BufferedImage DROP_DOWN_ICON = ImageUtil.loadImageResource(CompanionPetPlugin.class, "/dropdown_icon.png");
    private static final BufferedImage DROP_DOWN_ICON_FLIPPED = ImageUtil.loadImageResource(CompanionPetPlugin.class, "/dropdown_flipped_icon.png");
    private static final BufferedImage INVISIBLE_ICON = ImageUtil.loadImageResource(CompanionPetPlugin.class, "/invisible_icon.png");
    private static final BufferedImage VISIBLE_ICON = ImageUtil.loadImageResource(CompanionPetPlugin.class, "/visible_icon.png");
    private static final BufferedImage CHECKMARK = ImageUtil.loadImageResource(CompanionPetPlugin.class, "/checkmark.png");
    private static final BufferedImage NO_CHECKMARK = ImageUtil.loadImageResource(CompanionPetPlugin.class, "/no_checkmark.png");


    private final static String CONFIG_GROUP = "CompanionPetPlugin";


    public void sidePanelInitializer()
    {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.sidePanel.setLayout(new BoxLayout(this.sidePanel,BoxLayout.Y_AXIS));
        this.sidePanel.add(this.buildTitlePanel());
        this.sidePanel.add(this.buildCurrentPetTitle());
        this.sidePanel.add(this.buildCurrentPetPanel());
        this.sidePanel.add(this.buildThrallTitlePanel());
        this.sidePanel.add(this.buildThrallSpacerTop());
        this.sidePanel.add(this.buildSpacerAndTextForThralls());
        this.sidePanel.add(this.buildThrallPanel());
        this.sidePanel.add(this.buildSpacerPanelTop());
        this.sidePanel.add(this.buildPetSelectionTitle());
        this.sidePanel.add(this.buildFavPetButtonsPanel());
        this.sidePanel.add(this.buildSpacerPanelFavPets());
        this.sidePanel.add(this.buildPetButtonsPanel());
        this.sidePanel.add(this.buildSpacerPanelBottom());

        this.add(sidePanel, "North");
    }

    private JPanel buildTitlePanel()
    {
        titlePanel.removeAll();

        titlePanel.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));
        titlePanel.setLayout(new BorderLayout());
        PluginErrorPanel errorPanel = new PluginErrorPanel();
        errorPanel.setBorder(new EmptyBorder(2, 0, 3, 0));
        errorPanel.setFont(FontManager.getRunescapeBoldFont());
        errorPanel.setContent("Companion Pet Plugin", "Spawn any pet as a follower or thrall");
        titlePanel.add(errorPanel, "Center");
        return titlePanel;
    }


    private JPanel buildPetSelectionTitle()
    {
        petSelectionTitlePanel.removeAll();

        petSelectionTitlePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        petSelectionTitlePanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));

        petSelectionTitlePanel.setLayout(new FlowLayout());


        if (plugin.petData == null)
        {
            plugin.petData = PetData.pets.get(config.pet().getIdentifier());
        }

        BufferedImage bufferedIcon = config.filter() ? VISIBLE_ICON : INVISIBLE_ICON;
        String hoverText = config.filter() ? "Filter Pets" : "Un-Filter Pets";

        JButton button = new JButton(new ImageIcon(bufferedIcon));

        button.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(bufferedIcon, -80)));
        Dimension dimension = new Dimension(5,5);
        button.setSize(dimension);
        button.setMaximumSize(dimension);
        button.setToolTipText(hoverText);
        SwingUtil.removeButtonDecorations(button);

        button.addActionListener(e-> {

            update();

            BufferedImage icon = config.filter() ? VISIBLE_ICON : INVISIBLE_ICON;
            String text = config.filter() ? "Filter Pets" : "Un-Filter Pets";
            button.setToolTipText(text);
            button.setIcon(new ImageIcon(icon));
            button.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(icon, -80)));

        });

        BufferedImage dropdownIcon = config.showPetList() ? DROP_DOWN_ICON_FLIPPED : DROP_DOWN_ICON;
        String toolTipText = config.showPetList() ? "Hide Pet list" : "Show Pet list";

        JButton dropDownButton = new JButton(new ImageIcon(dropdownIcon));
        dropDownButton.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(dropdownIcon, -80)));
        dropDownButton.setSize(dimension);
        dropDownButton.setMaximumSize(dimension);
        dropDownButton.setToolTipText(toolTipText);
        SwingUtil.removeButtonDecorations(dropDownButton);

        dropDownButton.addActionListener(e-> {

           if (config.showPetList())
           {
               configManager.setConfiguration(CONFIG_GROUP,"showPets",false);
               petButtonsPanel.setVisible(false);
               spacerPanelBottom.setVisible(false);
           }
           else
           {
               configManager.setConfiguration(CONFIG_GROUP,"showPets",true);
               petButtonsPanel.setVisible(true);
               spacerPanelBottom.setVisible(true);
           }

            BufferedImage icon = config.showPetList() ? DROP_DOWN_ICON_FLIPPED : DROP_DOWN_ICON;
            String toolTip = config.showPetList() ? "Hide Pet list" : "Show Pet list";
            dropDownButton.setIcon(new ImageIcon(icon));
            dropDownButton.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(icon, -80)));
            dropDownButton.setToolTipText(toolTip);

        });

        if (!config.showPetList())
        {
            petButtonsPanel.setVisible(false);
            spacerPanelBottom.setVisible(false);
        }


        JLabel title = new JLabel("Pet Selector");
        title.setFont(FontManager.getRunescapeBoldFont());
        title.setForeground(Color.LIGHT_GRAY);

        petSelectionTitlePanel.add(dropDownButton,"Left");
        petSelectionTitlePanel.add(Box.createRigidArea(new Dimension(15, 0)),"Left");
        petSelectionTitlePanel.add(title,"Center");
        petSelectionTitlePanel.add(Box.createRigidArea(new Dimension(15, 0)),"Right");
        petSelectionTitlePanel.add(button,"Right");

        return petSelectionTitlePanel;
    }


    private void update()
    {
        configManager.setConfiguration(CONFIG_GROUP,"filter",!config.filter());
        buildPetButtonsPanel();
        //sleeping the swing thread for 1 client tick to allow the panel to update
        clientThread.invokeAtTickEnd(()->
        {
            petButtonsPanel.revalidate();
            petSelectionTitlePanel.revalidate();
            petButtonsPanel.repaint();
            petSelectionTitlePanel.repaint();
        });


    }


    private JPanel buildCurrentPetTitle()
    {
        currentPetTitle.removeAll();

        currentPetTitle.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));

        currentPetTitle.setLayout(new FlowLayout());


        if (plugin.petData == null)
        {
            plugin.petData = PetData.pets.get(config.pet().getIdentifier());
        }

        JLabel title = new JLabel("Current Pet");
        title.setFont(FontManager.getRunescapeBoldFont());
        title.setForeground(Color.LIGHT_GRAY);

        currentPetTitle.add(title,"Center");

        return currentPetTitle;
    }

    private JPanel buildCurrentPetPanel()
    {
        currentPetPanel.removeAll();

        currentPetPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 20, 0, 0), new MatteBorder(0, 0, 0, 0, new Color(42, 255, 0, 89))));

        currentPetPanel.setLayout(new BorderLayout());

        updateCurrentPetIcon();

        currentPetPanel.add(currentPetIcon,"Center");

        return currentPetPanel;
    }


    public void updateCurrentPetIcon()
    {
        if (plugin.petData == null)
        {
            plugin.petData = PetData.pets.get(config.pet().getIdentifier());
        }

        AsyncBufferedImage icon = itemManager.getImage(plugin.petData.getIconID());
        Runnable resize = () ->
        {
            currentPetIcon.setIcon(new ImageIcon(icon.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
            currentPetIcon.setToolTipText(plugin.petData.getName());
        };
        icon.onLoaded(resize);
        resize.run();
    }


    private JPanel buildThrallTitlePanel()
    {
        thrallTitlePanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, -3, 0), new MatteBorder(1, 0, 0, 0, new Color(42, 255, 0, 89))));
        JLabel title = new JLabel("Thralls");
        title.setFont(FontManager.getRunescapeBoldFont());

        BufferedImage dropdownIcon = config.showThralls() ? DROP_DOWN_ICON_FLIPPED : DROP_DOWN_ICON;
        String showHideThrallToolTip = config.showThralls() ? "Hide Thralls" : "Show Thralls";

        Dimension dimension = new Dimension(5,5);

        JButton dropDownButton = new JButton(new ImageIcon(dropdownIcon));
        dropDownButton.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(dropdownIcon, -80)));
        dropDownButton.setSize(dimension);
        dropDownButton.setMaximumSize(dimension);
        dropDownButton.setToolTipText(showHideThrallToolTip);
        SwingUtil.removeButtonDecorations(dropDownButton);

        dropDownButton.addActionListener(e-> {

            if (config.showThralls())
            {
                configManager.setConfiguration(CONFIG_GROUP,"showThralls",false);
                thrallPanel.setVisible(false);
                spacerAndTextForThralls.setVisible(false);
                thrallSpacerTop.setVisible(false);
                thrallTitlePanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, -10, 0), new MatteBorder(1, 0, 0, 0, new Color(42, 255, 0, 89))));

            }
            else
            {
                configManager.setConfiguration(CONFIG_GROUP,"showThralls",true);
                thrallPanel.setVisible(true);
                spacerAndTextForThralls.setVisible(true);
                thrallSpacerTop.setVisible(true);
                thrallTitlePanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, -3, 0), new MatteBorder(1, 0, 0, 0, new Color(42, 255, 0, 89))));

            }

            BufferedImage icon = config.showThralls() ? DROP_DOWN_ICON_FLIPPED : DROP_DOWN_ICON;
            String toolTip = config.showThralls() ? "Hide Thralls" : "Show Thralls";
            dropDownButton.setIcon(new ImageIcon(icon));
            dropDownButton.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(icon, -80)));
            dropDownButton.setToolTipText(toolTip);

        });


        BufferedImage checkboxIcon = config.companionThralls() ? CHECKMARK : NO_CHECKMARK;
        String checkboxToolTip = config.companionThralls() ? "Disable Companion Thralls" : "Enable Companion Thralls";

        JButton thrallCheckbox = new JButton(new ImageIcon(checkboxIcon));
        thrallCheckbox.setBorder(new EmptyBorder(-2,0,0,0));


        thrallCheckbox.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(checkboxIcon, -80)));
        thrallCheckbox.setSize(dimension);
        thrallCheckbox.setMaximumSize(dimension);
        thrallCheckbox.setToolTipText(checkboxToolTip);
        SwingUtil.removeButtonDecorations(thrallCheckbox);

        thrallCheckbox.addActionListener(e-> {

            if (config.companionThralls())
            {
                configManager.setConfiguration(CONFIG_GROUP,"companionThralls",false);
            }
            else
            {
                configManager.setConfiguration(CONFIG_GROUP,"companionThralls",true);
            }

            BufferedImage icon = config.companionThralls() ? CHECKMARK : NO_CHECKMARK;
            String toolTip = config.companionThralls() ? "Disable Companion Thralls" : "Enable Companion Thralls";
            thrallCheckbox.setIcon(new ImageIcon(icon));
            thrallCheckbox.setRolloverIcon(new ImageIcon(ImageUtil.luminanceOffset(icon, -80)));
            thrallCheckbox.setToolTipText(toolTip);

        });


        if (!config.showThralls())
        {
            thrallPanel.setVisible(false);
            spacerAndTextForThralls.setVisible(false);
            thrallSpacerTop.setVisible(false);
        }

        thrallTitlePanel.add(dropDownButton);
        thrallTitlePanel.add(Box.createRigidArea(new Dimension(33, 0)));
        thrallTitlePanel.add(title);
        thrallTitlePanel.add(Box.createRigidArea(new Dimension(36, 0)));
        thrallTitlePanel.add(thrallCheckbox);


        return thrallTitlePanel;
    }


    private JPanel buildSpacerAndTextForThralls()
    {
        spacerAndTextForThralls.removeAll();

        spacerAndTextForThralls.setBorder(new EmptyBorder(0, 5, 0, 2));
        JLabel melee = new JLabel("     Melee ");
        JLabel range = new JLabel("     Range");
        JLabel mage = new JLabel("      Mage");

        mage.setForeground(new Color(46, 169, 255, 89));
        melee.setForeground(new Color(255, 0, 0, 89));
        range.setForeground(new Color(42, 255, 0, 89));

        Font font = FontManager.getRunescapeBoldFont().deriveFont(Font.BOLD,13);

        melee.setFont(font);
        range.setFont(font);
        mage.setFont(font);

        melee.setBorder(new EmptyBorder(3, 0, 0, 0));
        range.setBorder(new EmptyBorder(3, 0, 0, 0));
        mage.setBorder(new EmptyBorder(3, 0, 0, 0));


        spacerAndTextForThralls.add(melee);
        spacerAndTextForThralls.add(range);
        spacerAndTextForThralls.add(mage);


        spacerAndTextForThralls.setLayout(new GridLayout(1,3,0,0));


        return spacerAndTextForThralls;
    }


    private JPanel buildThrallSpacerTop()
    {
        thrallSpacerTop.setBorder(new CompoundBorder(new EmptyBorder(-10, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));

        return thrallSpacerTop;
    }


    private JPanel buildSpacerPanelTop()
    {
        spacerPanelTop.setBorder(new CompoundBorder(new EmptyBorder(-3, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));
        return spacerPanelTop;
    }


    private JPanel buildSpacerPanelBottom()
    {
        spacerPanelBottom.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));
        return spacerPanelBottom;
    }


    //add fav pet drop down menu with icon button to trigger
    private JPanel buildFavPetButtonsPanel()
    {

        favPetButtonsPanel.removeAll();

        favPetButtonsPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        List<PetData> favorites = Arrays.stream(config.favorites().split(",")).map(PetData::valueOf).collect(Collectors.toList());

        for (PetData petData : favorites)
        {
            Icon icon = new ImageIcon(itemManager.getImage(petData.getIconID()));

            JButton button = new JButton(icon);
            button.setToolTipText(petData.getName());
            button.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(1, 1, 1, 1, ColorScheme.DARK_GRAY_COLOR),
                    new EmptyBorder(0, 5, 0, 0)
            ));

            JPopupMenu menu = new JPopupMenu();
            JMenuItem setCurrentPetAsFav = new JMenuItem("Swap With Current Pet");
            setCurrentPetAsFav.addActionListener(e -> updateFavPet(petData));

            menu.add(setCurrentPetAsFav);

            buildThrallPopUpMenu(petData,menu);

            button.setComponentPopupMenu(menu);

            button.addActionListener(e-> clientThread.invokeLater(()-> plugin.updatePet(petData)));
            favPetButtonsPanel.add(button);
        }

        favPetButtonsPanel.setLayout(new GridLayout(1,4,2,2));

        return favPetButtonsPanel;
    }

    private JPanel buildThrallPanel()
    {

        thrallPanel.removeAll();

        ArrayList<PetData> thralls = new ArrayList<>();
        thralls.add(config.meleeThrall());
        thralls.add(config.rangeThrall());
        thralls.add(config.mageThrall());

        for (PetData petData : thralls)
        {
            Icon icon = new ImageIcon(itemManager.getImage(petData.getIconID()));

            JLabel label = new JLabel(icon);
            label.setToolTipText(petData.getName());
            label.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(1, 1, 1, 1, ColorScheme.DARK_GRAY_COLOR),
                    new EmptyBorder(0, 5, 0, 0)
            ));


            JPopupMenu menu = new JPopupMenu();
            JMenuItem setCurrentPetAsThrall = new JMenuItem("Set Current Pet As Thrall");

            setCurrentPetAsThrall.addActionListener(e ->
            {
                switch (thralls.indexOf(petData))
                {
                    case 0:
                    configManager.setConfiguration(CONFIG_GROUP,"meleeThrall",plugin.petData);
                    updateThrallPanel();
                    break;

                    case 1:
                    configManager.setConfiguration(CONFIG_GROUP,"rangeThrall",plugin.petData);
                    updateThrallPanel();
                    break;

                    case 2:
                    configManager.setConfiguration(CONFIG_GROUP,"mageThrall",plugin.petData);
                    updateThrallPanel();
                    break;
                }

            });

            menu.add(setCurrentPetAsThrall);

            label.setComponentPopupMenu(menu);

            thrallPanel.add(label);
        }

        thrallPanel.setBorder(new EmptyBorder(0, 0, -5, 2));
        thrallPanel.setLayout(new GridLayout(1,3,0,0));

        return thrallPanel;
    }


    private void buildThrallPopUpMenu(PetData petData, JPopupMenu menu)
    {

        JMenuItem meleeThrall = new JMenuItem("Set Melee Thrall");
        JMenuItem rangeThrall = new JMenuItem("Set Range Thrall");
        JMenuItem mageThrall = new JMenuItem("Set Mage Thrall");

        meleeThrall.addActionListener(e -> {
            configManager.setConfiguration(CONFIG_GROUP,"meleeThrall",petData);
            updateThrallPanel();
        });

        rangeThrall.addActionListener(e -> {
            configManager.setConfiguration(CONFIG_GROUP,"rangeThrall",petData);
            updateThrallPanel();
        });

        mageThrall.addActionListener(e -> {
            configManager.setConfiguration(CONFIG_GROUP,"mageThrall",petData);
            updateThrallPanel();
        });

        menu.add(meleeThrall);
        menu.add(rangeThrall);
        menu.add(mageThrall);

    }

    private void updateThrallPanel()
    {
        buildThrallPanel();

        clientThread.invokeAtTickEnd(()->
        {
            thrallPanel.revalidate();
            thrallPanel.repaint();
        });
    }


    private void updateFavPet(PetData petData)
    {
        if (plugin.petData.equals(petData) || config.favorites().contains(plugin.petData.toString()))
        {
            return;
        }

        String s = config.favorites();
        s = s.replaceFirst(petData.toString(),plugin.petData.toString());
        configManager.setConfiguration(CONFIG_GROUP,"favorites",s);

        buildFavPetButtonsPanel();

        clientThread.invokeAtTickEnd(()->
        {
            favPetButtonsPanel.revalidate();
            favPetButtonsPanel.repaint();
        });


    }


    private JPanel buildSpacerPanelFavPets()
    {
        spacerPanelFavPets.setBorder(new CompoundBorder(new EmptyBorder(-5, 0, 0, 0), new MatteBorder(0, 0, 1, 0, new Color(42, 255, 0, 89))));
        return spacerPanelFavPets;
    }



    private JPanel buildPetButtonsPanel()
    {

        petButtonsPanel.removeAll();

        petButtonsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        PetData[] petList = config.filter() ?  PetData.values() : PetData.petsToShow.toArray(new PetData[0]);

        for (PetData petData : petList)
        {
            if (!petData.isWorking() && !config.allowBrokenPets())
            {
                continue;
            }

            Icon icon = new ImageIcon(itemManager.getImage(petData.getIconID()));

            JButton button = new JButton(icon);
            button.setToolTipText(petData.getName());
            button.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(1, 1, 1, 1, ColorScheme.DARK_GRAY_COLOR),
                    new EmptyBorder(0, 5, 0, 0)
            ));

            JPopupMenu menu = new JPopupMenu();

            buildThrallPopUpMenu(petData,menu);

            button.setComponentPopupMenu(menu);

            button.addActionListener(e-> clientThread.invokeLater(()-> plugin.updatePet(petData)));
            petButtonsPanel.add(button);
        }

        final int rowSize = ((petButtonsPanel.getComponents().length % 4 == 0) ? 0 : 1) + petButtonsPanel.getComponents().length / 4;
        petButtonsPanel.setLayout(new GridLayout(rowSize,4,2,2));

        return petButtonsPanel;
    }


}
