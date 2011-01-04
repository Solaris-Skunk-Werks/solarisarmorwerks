/*
Copyright (c) 2008~2009, Justin R. Bengtson (poopshotgun@yahoo.com)
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
        this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the
        documentation and/or other materials provided with the distribution.
    * Neither the name of Justin R. Bengtson nor the names of contributors may
        be used to endorse or promote products derived from this software
        without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package saw.gui;

import battleforce.BattleForceStats;
import common.CommonTools;
import common.Constants;
import components.AvailableCode;
import components.CVArmor;
import components.CombatVehicle;
import components.LocationIndex;
import components.abPlaceable;
import filehandlers.Media;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.prefs.Preferences;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import states.ifState;

public class frmVee extends javax.swing.JFrame {
    CombatVehicle CurVee;
    abPlaceable CurItem;
    Preferences Prefs;
    String[] Selections = { "", "" };

    FocusAdapter spinners = new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            if ( e.getSource() instanceof JTextComponent ) {
                final JTextComponent textComponent = ((JTextComponent)e.getSource());
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        textComponent.selectAll();
                    }
                });
            }
        }
    };
    
    /** Creates new form frmMain2 */
    public frmVee() {
        initComponents();

        Prefs = Preferences.userRoot().node( Constants.SAWPrefs );
        CurVee = new CombatVehicle( );
        cmbMotiveTypeActionPerformed(null);

        setTitle( saw.Constants.AppDescription + " " + saw.Constants.Version );

        // set the program options
        cmbRulesLevel.setSelectedItem( Prefs.get( "NewMech_RulesLevel", "Tournament Legal" ) );
        cmbEra.setSelectedItem( Prefs.get( "NewMech_Era", "Age of War/Star League" ) );
        BuildTechBaseSelector();
        cmbTechBase.setSelectedItem( Prefs.get( "NewMech_Techbase", "Inner Sphere" ) );
        BuildEngineSelector();
        BuildArmorSelector();
        FixArmorSpinners();
        RefreshSummary();
        RefreshInfoPane();

        //Makes spinners auto-select-all text for easier entry
        ((JSpinner.DefaultEditor)spnTonnage.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnCruiseMP.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnJumpMP.getEditor()).getTextField().addFocusListener(spinners);

        ((JSpinner.DefaultEditor)spnFrontArmor.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnLeftArmor.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnRightArmor.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnRearArmor.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnTurretArmor.getEditor()).getTextField().addFocusListener(spinners);
        ((JSpinner.DefaultEditor)spnRearTurretArmor.getEditor()).getTextField().addFocusListener(spinners);

    }

        public void RefreshInfoPane() {
        // refreshes the information pane at the bottom of the screen
        // set the colors
        if( CurVee.GetCurrentTons() > CurVee.GetTonnage() ) {
            txtInfoTonnage.setForeground( Color.RED );
            txtInfoFreeTons.setForeground( Color.RED );
        } else {
            txtInfoTonnage.setForeground( Color.GREEN );
            txtInfoFreeTons.setForeground( Color.GREEN );
        }
        // fill in the info
        if( CurVee.UsingFractionalAccounting() ) {
            txtInfoTonnage.setText( "Tons: " + CommonTools.RoundFractionalTons( CurVee.GetCurrentTons() ) );
            txtInfoFreeTons.setText( "Free Tons: " + CommonTools.RoundFractionalTons( CurVee.GetTonnage() - CurVee.GetCurrentTons() ) );
        } else {
            txtInfoTonnage.setText( "Tons: " + CurVee.GetCurrentTons() );
            txtInfoFreeTons.setText( "Free Tons: " + ( CurVee.GetTonnage() - CurVee.GetCurrentTons() ) );
        }
        txtInfoBattleValue.setText( "BV: " + String.format( "%1$,d", CurVee.GetCurrentBV() ) );
        txtInfoCost.setText( "Cost: " + String.format( "%1$,.0f", Math.floor( CurVee.GetTotalCost() + 0.5f ) ) );

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlInfoPane = new javax.swing.JPanel();
        txtInfoTonnage = new javax.swing.JTextField();
        txtInfoFreeTons = new javax.swing.JTextField();
        txtInfoFreeCrits = new javax.swing.JTextField();
        txtInfoUnplaced = new javax.swing.JTextField();
        txtInfoBattleValue = new javax.swing.JTextField();
        txtInfoCost = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnPostToS7 = new javax.swing.JButton();
        jSeparator25 = new javax.swing.JToolBar.Separator();
        btnAddToForceList = new javax.swing.JButton();
        btnForceList = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        tbpMainTabPane = new javax.swing.JTabbedPane();
        pnlBasicSetup = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtVehicleName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbRulesLevel = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmbEra = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbTechBase = new javax.swing.JComboBox();
        lblEraYears = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProdYear = new javax.swing.JTextField();
        chkYearRestrict = new javax.swing.JCheckBox();
        jLabel81 = new javax.swing.JLabel();
        txtSource = new javax.swing.JTextField();
        pnlMovement = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        spnCruiseMP = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        lblFlankMP = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spnJumpMP = new javax.swing.JSpinner();
        pnlChassis = new javax.swing.JPanel();
        cmbMotiveType = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        spnTonnage = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        cmbEngineType = new javax.swing.JComboBox();
        chkTrailer = new javax.swing.JCheckBox();
        lblVeeClass = new javax.swing.JLabel();
        chkOmniVee = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        pnlSummary = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSumIntTons = new javax.swing.JTextField();
        txtSumIntAV = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSumEngTons = new javax.swing.JTextField();
        txtSumEngAV = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtSumLifTons = new javax.swing.JTextField();
        txtSumLifAV = new javax.swing.JTextField();
        txtSumEngSpace = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtSumConTons = new javax.swing.JTextField();
        txtSumConAV = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtSumJJTons = new javax.swing.JTextField();
        txtSumJJSpace = new javax.swing.JTextField();
        txtSumJJAV = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtSumHSTons = new javax.swing.JTextField();
        txtSumHSAV = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtSumArmTons = new javax.swing.JTextField();
        txtSumArmSpace = new javax.swing.JTextField();
        txtSumArmAV = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtSumTurTons = new javax.swing.JTextField();
        txtSumTurAV = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtSumRTuTons = new javax.swing.JTextField();
        txtSumRTuAV = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtSumSpnTons = new javax.swing.JTextField();
        txtSumSpnAV = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSumPATons = new javax.swing.JTextField();
        txtSumPAAV = new javax.swing.JTextField();
        pnlInformation = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        lblSupensionFacter = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblMinEngineTons = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblBaseEngineRating = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lblFinalEngineRating = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblFreeHeatSinks = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblNumCrew = new javax.swing.JLabel();
        pnlChassisMods = new javax.swing.JPanel();
        chkFlotationHull = new javax.swing.JCheckBox();
        chkLimitedAmph = new javax.swing.JCheckBox();
        chkFullAmph = new javax.swing.JCheckBox();
        chkDuneBuggy = new javax.swing.JCheckBox();
        chkEnviroSealing = new javax.swing.JCheckBox();
        pnlExperimental = new javax.swing.JPanel();
        chkArmoredMotive = new javax.swing.JCheckBox();
        chkCommandConsole = new javax.swing.JCheckBox();
        chkEscapePod = new javax.swing.JCheckBox();
        chkMinesweeper = new javax.swing.JCheckBox();
        chkJetBooster = new javax.swing.JCheckBox();
        chkSupercharger = new javax.swing.JCheckBox();
        lblSupercharger = new javax.swing.JLabel();
        cmbSCLoc = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pnlRightArmor = new javax.swing.JPanel();
        lblRightIntPts = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        spnRightArmor = new javax.swing.JSpinner();
        pnlFrontArmor = new javax.swing.JPanel();
        lblFrontIntPts = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        spnFrontArmor = new javax.swing.JSpinner();
        pnlLeftArmor = new javax.swing.JPanel();
        lblLeftIntPts = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        spnLeftArmor = new javax.swing.JSpinner();
        pnlRearArmor = new javax.swing.JPanel();
        lblRearIntPts = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        spnRearArmor = new javax.swing.JSpinner();
        pnlTurretArmor = new javax.swing.JPanel();
        lblTurretIntPts = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        spnTurretArmor = new javax.swing.JSpinner();
        pnlRearTurretArmor = new javax.swing.JPanel();
        lblRearTurretIntPts = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        spnRearTurretArmor = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        cmbArmorType = new javax.swing.JComboBox();
        chkBalanceLRArmor = new javax.swing.JCheckBox();
        chkBalanceFRArmor = new javax.swing.JCheckBox();
        btnSetArmorTons = new javax.swing.JButton();
        btnUseRemaining = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblArmorTotals = new javax.swing.JLabel();
        lblArmorCoverage = new javax.swing.JLabel();
        txtArmorTons = new javax.swing.JTextField();
        txtArmorSpace = new javax.swing.JTextField();
        lblArmorTonsWasted = new javax.swing.JLabel();
        lblArmorLeftInLot = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tbpWeaponChooser = new javax.swing.JTabbedPane();
        pnlBallistic = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstChooseBallistic = new javax.swing.JList();
        jSeparator6 = new javax.swing.JSeparator();
        pnlEnergy = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstChooseEnergy = new javax.swing.JList();
        jSeparator8 = new javax.swing.JSeparator();
        pnlMissile = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane19 = new javax.swing.JScrollPane();
        lstChooseMissile = new javax.swing.JList();
        jSeparator10 = new javax.swing.JSeparator();
        pnlPhysical = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane20 = new javax.swing.JScrollPane();
        lstChoosePhysical = new javax.swing.JList();
        jSeparator12 = new javax.swing.JSeparator();
        pnlEquipmentChooser = new javax.swing.JPanel();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane21 = new javax.swing.JScrollPane();
        lstChooseEquipment = new javax.swing.JList();
        jSeparator14 = new javax.swing.JSeparator();
        pnlArtillery = new javax.swing.JPanel();
        jSeparator18 = new javax.swing.JSeparator();
        jScrollPane24 = new javax.swing.JScrollPane();
        lstChooseArtillery = new javax.swing.JList();
        jSeparator19 = new javax.swing.JSeparator();
        pnlAmmunition = new javax.swing.JPanel();
        jSeparator15 = new javax.swing.JSeparator();
        jScrollPane22 = new javax.swing.JScrollPane();
        lstChooseAmmunition = new javax.swing.JList();
        jSeparator16 = new javax.swing.JSeparator();
        pnlSpecials = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        chkUseTC = new javax.swing.JCheckBox();
        chkFCSAIV = new javax.swing.JCheckBox();
        chkFCSAV = new javax.swing.JCheckBox();
        chkFCSApollo = new javax.swing.JCheckBox();
        chkClanCASE = new javax.swing.JCheckBox();
        pnlSelected = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        lstSelectedEquipment = new javax.swing.JList();
        pnlEquipInfo = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblInfoAVSL = new javax.swing.JLabel();
        lblInfoAVSW = new javax.swing.JLabel();
        lblInfoAVCI = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        lblInfoIntro = new javax.swing.JLabel();
        lblInfoExtinct = new javax.swing.JLabel();
        lblInfoReintro = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        lblInfoName = new javax.swing.JLabel();
        lblInfoType = new javax.swing.JLabel();
        lblInfoHeat = new javax.swing.JLabel();
        lblInfoDamage = new javax.swing.JLabel();
        lblInfoRange = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        lblInfoAmmo = new javax.swing.JLabel();
        lblInfoTonnage = new javax.swing.JLabel();
        lblInfoCrits = new javax.swing.JLabel();
        lblInfoSpecials = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel66 = new javax.swing.JLabel();
        lblInfoCost = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lblInfoBV = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        lblInfoMountRestrict = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        lblInfoRulesLevel = new javax.swing.JLabel();
        pnlControls = new javax.swing.JPanel();
        btnRemoveEquip = new javax.swing.JButton();
        btnClearEquip = new javax.swing.JButton();
        btnAddEquip = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        pnlBFStats = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        lblBFMV = new javax.swing.JLabel();
        lblBFWt = new javax.swing.JLabel();
        lblBFOV = new javax.swing.JLabel();
        lblBFExtreme = new javax.swing.JLabel();
        lblBFShort = new javax.swing.JLabel();
        lblBFMedium = new javax.swing.JLabel();
        lblBFLong = new javax.swing.JLabel();
        lblBFArmor = new javax.swing.JLabel();
        lblBFStructure = new javax.swing.JLabel();
        lblBFSA = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        lblBFPoints = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextAreaBFConversion = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        txtInfoTonnage.setEditable(false);
        txtInfoTonnage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInfoTonnage.setText("Tonnage: 000.00");
        txtInfoTonnage.setMaximumSize(new java.awt.Dimension(110, 20));
        txtInfoTonnage.setMinimumSize(new java.awt.Dimension(110, 20));
        txtInfoTonnage.setPreferredSize(new java.awt.Dimension(110, 20));
        pnlInfoPane.add(txtInfoTonnage);

        txtInfoFreeTons.setEditable(false);
        txtInfoFreeTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInfoFreeTons.setText("Free Tons: 000.00");
        txtInfoFreeTons.setMaximumSize(new java.awt.Dimension(115, 20));
        txtInfoFreeTons.setMinimumSize(new java.awt.Dimension(115, 20));
        txtInfoFreeTons.setPreferredSize(new java.awt.Dimension(115, 20));
        pnlInfoPane.add(txtInfoFreeTons);

        txtInfoFreeCrits.setEditable(false);
        txtInfoFreeCrits.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInfoFreeCrits.setText("Space: 00");
        txtInfoFreeCrits.setMaximumSize(new java.awt.Dimension(65, 20));
        txtInfoFreeCrits.setMinimumSize(new java.awt.Dimension(65, 20));
        txtInfoFreeCrits.setPreferredSize(new java.awt.Dimension(65, 20));
        pnlInfoPane.add(txtInfoFreeCrits);

        txtInfoUnplaced.setEditable(false);
        txtInfoUnplaced.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInfoUnplaced.setText("Unplaced Items: 00");
        txtInfoUnplaced.setMaximumSize(new java.awt.Dimension(120, 20));
        txtInfoUnplaced.setMinimumSize(new java.awt.Dimension(120, 20));
        txtInfoUnplaced.setPreferredSize(new java.awt.Dimension(120, 20));
        pnlInfoPane.add(txtInfoUnplaced);

        txtInfoBattleValue.setEditable(false);
        txtInfoBattleValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInfoBattleValue.setText("BV: 00,000");
        txtInfoBattleValue.setMaximumSize(new java.awt.Dimension(75, 20));
        txtInfoBattleValue.setMinimumSize(new java.awt.Dimension(75, 20));
        txtInfoBattleValue.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlInfoPane.add(txtInfoBattleValue);

        txtInfoCost.setEditable(false);
        txtInfoCost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInfoCost.setText("Cost: 000,000,000,000.00");
        txtInfoCost.setMaximumSize(new java.awt.Dimension(165, 20));
        txtInfoCost.setMinimumSize(new java.awt.Dimension(165, 20));
        txtInfoCost.setPreferredSize(new java.awt.Dimension(165, 20));
        pnlInfoPane.add(txtInfoCost);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/document--plus.png"))); // NOI18N
        jButton1.setText("New");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/folder-open-document.png"))); // NOI18N
        jButton2.setText("Open");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/disk-black.png"))); // NOI18N
        jButton7.setText("Save");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);
        jToolBar1.add(jSeparator1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/printer.png"))); // NOI18N
        jButton3.setText("Print");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/projection-screen.png"))); // NOI18N
        jButton4.setText("Preview");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator2);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/document--arrow.png"))); // NOI18N
        jButton5.setText("Export");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator3);

        btnPostToS7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/server.png"))); // NOI18N
        btnPostToS7.setText("S7");
        btnPostToS7.setToolTipText("Upload to Solaris7.com");
        btnPostToS7.setFocusable(false);
        btnPostToS7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPostToS7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPostToS7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostToS7ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPostToS7);
        jToolBar1.add(jSeparator25);

        btnAddToForceList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/clipboard--plus.png"))); // NOI18N
        btnAddToForceList.setText("Add");
        btnAddToForceList.setToolTipText("Add  to Force List");
        btnAddToForceList.setFocusable(false);
        btnAddToForceList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddToForceList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddToForceList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToForceListActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAddToForceList);

        btnForceList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/clipboard.png"))); // NOI18N
        btnForceList.setText("Force");
        btnForceList.setToolTipText("Force List");
        btnForceList.setFocusable(false);
        btnForceList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnForceList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnForceList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForceListActionPerformed(evt);
            }
        });
        jToolBar1.add(btnForceList);
        jToolBar1.add(jSeparator4);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saw/images/gear.png"))); // NOI18N
        jButton6.setText("Options");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Basic Information"));

        jLabel1.setText("Vehicle Name:");

        txtVehicleName.setFocusCycleRoot(true);
        txtVehicleName.setMinimumSize(new java.awt.Dimension(150, 20));
        txtVehicleName.setPreferredSize(new java.awt.Dimension(150, 20));

        jLabel4.setText("Model:");

        txtModel.setMinimumSize(new java.awt.Dimension(150, 20));
        txtModel.setPreferredSize(new java.awt.Dimension(150, 20));

        jLabel2.setText("Rules Level:");

        cmbRulesLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tournament Legal", "Advanced", "Experimental" }));
        cmbRulesLevel.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbRulesLevel.setPreferredSize(new java.awt.Dimension(150, 20));
        cmbRulesLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRulesLevelActionPerformed(evt);
            }
        });

        jLabel5.setText("Era:");

        cmbEra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age of War/Star League", "Succession Wars", "Clan Invasion", "All Eras (non-canon)" }));
        cmbEra.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbEra.setPreferredSize(new java.awt.Dimension(150, 20));

        jLabel3.setText("Tech Base:");

        cmbTechBase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inner Sphere", "Clan", "Mixed Tech" }));
        cmbTechBase.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbTechBase.setPreferredSize(new java.awt.Dimension(150, 20));

        lblEraYears.setText("2443~2800");

        jLabel6.setText("Production Year:");

        txtProdYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProdYear.setMinimumSize(new java.awt.Dimension(150, 20));
        txtProdYear.setPreferredSize(new java.awt.Dimension(150, 20));

        chkYearRestrict.setText("Restrict Availability by Year");

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel81.setText("Source:");

        txtSource.setMinimumSize(new java.awt.Dimension(150, 20));
        txtSource.setPreferredSize(new java.awt.Dimension(150, 20));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(cmbRulesLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5)
                        .addGap(2, 2, 2)
                        .addComponent(cmbEra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(cmbTechBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(lblEraYears))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2)
                        .addComponent(txtProdYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(chkYearRestrict))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel81)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(cmbRulesLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(cmbEra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(cmbTechBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lblEraYears)
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(txtProdYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(chkYearRestrict)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pnlMovement.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Movement"));

        jLabel10.setText("Cruise MP:");

        spnCruiseMP.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spnCruiseMP.setMinimumSize(new java.awt.Dimension(45, 20));
        spnCruiseMP.setPreferredSize(new java.awt.Dimension(45, 20));
        spnCruiseMP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCruiseMPStateChanged(evt);
            }
        });

        jLabel11.setText("Flank MP:");

        lblFlankMP.setText("00");

        jLabel13.setText("Jump MP:");

        spnJumpMP.setMinimumSize(new java.awt.Dimension(45, 20));
        spnJumpMP.setPreferredSize(new java.awt.Dimension(45, 20));

        javax.swing.GroupLayout pnlMovementLayout = new javax.swing.GroupLayout(pnlMovement);
        pnlMovement.setLayout(pnlMovementLayout);
        pnlMovementLayout.setHorizontalGroup(
            pnlMovementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovementLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlMovementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMovementLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(2, 2, 2)
                        .addComponent(spnCruiseMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMovementLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(lblFlankMP))
                    .addGroup(pnlMovementLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel13)
                        .addGap(2, 2, 2)
                        .addComponent(spnJumpMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        pnlMovementLayout.setVerticalGroup(
            pnlMovementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovementLayout.createSequentialGroup()
                .addGroup(pnlMovementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMovementLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(spnCruiseMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlMovementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lblFlankMP))
                .addGap(2, 2, 2)
                .addGroup(pnlMovementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMovementLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13))
                    .addComponent(spnJumpMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChassis.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chassis"));

        cmbMotiveType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hovercraft", "Naval (Displacement)", "Naval (Hydrofoil)", "Naval (Submarine)", "Tracked", "VTOL", "Wheeled", "WiGE" }));
        cmbMotiveType.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbMotiveType.setPreferredSize(new java.awt.Dimension(150, 20));
        cmbMotiveType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMotiveTypeActionPerformed(evt);
            }
        });

        jLabel7.setText("Motive Type:");

        jLabel8.setText("Tonnage:");

        spnTonnage.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spnTonnage.setMinimumSize(new java.awt.Dimension(45, 20));
        spnTonnage.setPreferredSize(new java.awt.Dimension(45, 20));
        spnTonnage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                spnTonnageFocusGained(evt);
            }
        });

        jLabel9.setText("Engine:");

        cmbEngineType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I.C.E.", "Fusion", "Light Fusion", "XL Fusion", "Compact Fusion" }));
        cmbEngineType.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbEngineType.setPreferredSize(new java.awt.Dimension(150, 20));

        chkTrailer.setText("Trailer");

        lblVeeClass.setText("Light Vee");

        chkOmniVee.setText("OmniVehicle");

        jLabel32.setText("Turret:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single Turret", "Dual Turret", "Sponson Turrets", "Chin Turret", "Mast Turret" }));

        javax.swing.GroupLayout pnlChassisLayout = new javax.swing.GroupLayout(pnlChassis);
        pnlChassis.setLayout(pnlChassisLayout);
        pnlChassisLayout.setHorizontalGroup(
            pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChassisLayout.createSequentialGroup()
                .addGroup(pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addComponent(cmbMotiveType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8)
                        .addGap(2, 2, 2)
                        .addComponent(spnTonnage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblVeeClass))
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(chkOmniVee))
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(chkTrailer))
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChassisLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChassisLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(2, 2, 2)
                                .addComponent(cmbEngineType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlChassisLayout.setVerticalGroup(
            pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChassisLayout.createSequentialGroup()
                .addGroup(pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(cmbMotiveType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(spnTonnage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblVeeClass)))
                .addComponent(chkOmniVee)
                .addComponent(chkTrailer)
                .addGap(2, 2, 2)
                .addGroup(pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChassisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(cmbEngineType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChassisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pnlSummary.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Summary"));
        pnlSummary.setLayout(new java.awt.GridBagLayout());

        jLabel12.setText("Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel12, gridBagConstraints);

        jLabel14.setText("Tonnage");
        pnlSummary.add(jLabel14, new java.awt.GridBagConstraints());

        jLabel15.setText("Space");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        pnlSummary.add(jLabel15, gridBagConstraints);

        jLabel16.setText("Availability");
        pnlSummary.add(jLabel16, new java.awt.GridBagConstraints());

        jLabel17.setText("Internal Structure:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel17, gridBagConstraints);

        txtSumIntTons.setEditable(false);
        txtSumIntTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumIntTons.setText("000.00");
        txtSumIntTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumIntTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumIntTons, gridBagConstraints);

        txtSumIntAV.setEditable(false);
        txtSumIntAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumIntAV.setText("X/X-X-X");
        txtSumIntAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumIntAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumIntAV, gridBagConstraints);

        jLabel18.setText("Engine:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel18, gridBagConstraints);

        txtSumEngTons.setEditable(false);
        txtSumEngTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumEngTons.setText("000.00");
        txtSumEngTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumEngTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumEngTons, gridBagConstraints);

        txtSumEngAV.setEditable(false);
        txtSumEngAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumEngAV.setText("X/X-X-X");
        txtSumEngAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumEngAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumEngAV, gridBagConstraints);

        jLabel19.setText("Lift/Dive/Rotor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel19, gridBagConstraints);

        txtSumLifTons.setEditable(false);
        txtSumLifTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumLifTons.setText("000.00");
        txtSumLifTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumLifTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumLifTons, gridBagConstraints);

        txtSumLifAV.setEditable(false);
        txtSumLifAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumLifAV.setText("X/X-X-X");
        txtSumLifAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumLifAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumLifAV, gridBagConstraints);

        txtSumEngSpace.setEditable(false);
        txtSumEngSpace.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumEngSpace.setText("00");
        txtSumEngSpace.setMinimumSize(new java.awt.Dimension(40, 20));
        txtSumEngSpace.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        pnlSummary.add(txtSumEngSpace, gridBagConstraints);

        jLabel20.setText("Controls:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel20, gridBagConstraints);

        txtSumConTons.setEditable(false);
        txtSumConTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumConTons.setText("000.00");
        txtSumConTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumConTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumConTons, gridBagConstraints);

        txtSumConAV.setEditable(false);
        txtSumConAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumConAV.setText("X/X-X-X");
        txtSumConAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumConAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumConAV, gridBagConstraints);

        jLabel21.setText("Jump Jets:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel21, gridBagConstraints);

        txtSumJJTons.setEditable(false);
        txtSumJJTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumJJTons.setText("000.00");
        txtSumJJTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumJJTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumJJTons, gridBagConstraints);

        txtSumJJSpace.setEditable(false);
        txtSumJJSpace.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumJJSpace.setText("00");
        txtSumJJSpace.setMinimumSize(new java.awt.Dimension(40, 20));
        txtSumJJSpace.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        pnlSummary.add(txtSumJJSpace, gridBagConstraints);

        txtSumJJAV.setEditable(false);
        txtSumJJAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumJJAV.setText("X/X-X-X");
        txtSumJJAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumJJAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumJJAV, gridBagConstraints);

        jLabel22.setText("Heat Sinks:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel22, gridBagConstraints);

        txtSumHSTons.setEditable(false);
        txtSumHSTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumHSTons.setText("000.00");
        txtSumHSTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumHSTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumHSTons, gridBagConstraints);

        txtSumHSAV.setEditable(false);
        txtSumHSAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumHSAV.setText("X/X-X-X");
        txtSumHSAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumHSAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumHSAV, gridBagConstraints);

        jLabel23.setText("Armor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel23, gridBagConstraints);

        txtSumArmTons.setEditable(false);
        txtSumArmTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumArmTons.setText("000.00");
        txtSumArmTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumArmTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumArmTons, gridBagConstraints);

        txtSumArmSpace.setEditable(false);
        txtSumArmSpace.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumArmSpace.setText("00");
        txtSumArmSpace.setMinimumSize(new java.awt.Dimension(40, 20));
        txtSumArmSpace.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        pnlSummary.add(txtSumArmSpace, gridBagConstraints);

        txtSumArmAV.setEditable(false);
        txtSumArmAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumArmAV.setText("X/X-X-X");
        txtSumArmAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumArmAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumArmAV, gridBagConstraints);

        jLabel24.setText("Turret:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel24, gridBagConstraints);

        txtSumTurTons.setEditable(false);
        txtSumTurTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumTurTons.setText("000.00");
        txtSumTurTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumTurTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumTurTons, gridBagConstraints);

        txtSumTurAV.setEditable(false);
        txtSumTurAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumTurAV.setText("X/X-X-X");
        txtSumTurAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumTurAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumTurAV, gridBagConstraints);

        jLabel25.setText("Rear Turret:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel25, gridBagConstraints);

        txtSumRTuTons.setEditable(false);
        txtSumRTuTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumRTuTons.setText("000.00");
        txtSumRTuTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumRTuTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumRTuTons, gridBagConstraints);

        txtSumRTuAV.setEditable(false);
        txtSumRTuAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumRTuAV.setText("X/X-X-X");
        txtSumRTuAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumRTuAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumRTuAV, gridBagConstraints);

        jLabel26.setText("Sponsoons:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel26, gridBagConstraints);

        txtSumSpnTons.setEditable(false);
        txtSumSpnTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumSpnTons.setText("000.00");
        txtSumSpnTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumSpnTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumSpnTons, gridBagConstraints);

        txtSumSpnAV.setEditable(false);
        txtSumSpnAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumSpnAV.setText("X/X-X-X");
        txtSumSpnAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumSpnAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumSpnAV, gridBagConstraints);

        jLabel27.setText("Power Amplifiers:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlSummary.add(jLabel27, gridBagConstraints);

        txtSumPATons.setEditable(false);
        txtSumPATons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumPATons.setText("000.00");
        txtSumPATons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtSumPATons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlSummary.add(txtSumPATons, gridBagConstraints);

        txtSumPAAV.setEditable(false);
        txtSumPAAV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSumPAAV.setText("X/X-X-X");
        txtSumPAAV.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSumPAAV.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSummary.add(txtSumPAAV, gridBagConstraints);

        pnlInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Information"));

        jLabel29.setText("Suspension Factor:");

        lblSupensionFacter.setText("000");

        jLabel31.setText("Minimum Engine Tonnage:");

        lblMinEngineTons.setText("000.00");

        jLabel33.setText("Base Engine Rating:");

        lblBaseEngineRating.setText("000");

        jLabel35.setText("Final Engine Rating:");

        lblFinalEngineRating.setText("000");

        jLabel28.setText("Free Heat Sinks:");

        lblFreeHeatSinks.setText("000");

        jLabel30.setText("Crew:");

        lblNumCrew.setText("00");

        javax.swing.GroupLayout pnlInformationLayout = new javax.swing.GroupLayout(pnlInformation);
        pnlInformation.setLayout(pnlInformationLayout);
        pnlInformationLayout.setHorizontalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel30)
                        .addGap(4, 4, 4)
                        .addComponent(lblNumCrew))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel29)
                        .addGap(4, 4, 4)
                        .addComponent(lblSupensionFacter))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(4, 4, 4)
                        .addComponent(lblMinEngineTons))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel33)
                        .addGap(4, 4, 4)
                        .addComponent(lblBaseEngineRating))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel35)
                        .addGap(4, 4, 4)
                        .addComponent(lblFinalEngineRating))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel28)
                        .addGap(4, 4, 4)
                        .addComponent(lblFreeHeatSinks)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pnlInformationLayout.setVerticalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(lblNumCrew))
                .addGap(2, 2, 2)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(lblSupensionFacter))
                .addGap(2, 2, 2)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(lblMinEngineTons))
                .addGap(2, 2, 2)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(lblBaseEngineRating))
                .addGap(2, 2, 2)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(lblFinalEngineRating))
                .addGap(2, 2, 2)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(lblFreeHeatSinks))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChassisMods.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chassis Modifications"));
        pnlChassisMods.setLayout(new java.awt.GridBagLayout());

        chkFlotationHull.setText("Flotation Hull");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlChassisMods.add(chkFlotationHull, gridBagConstraints);

        chkLimitedAmph.setText("Limited Amphibious");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlChassisMods.add(chkLimitedAmph, gridBagConstraints);

        chkFullAmph.setText("Fully Amphibious");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlChassisMods.add(chkFullAmph, gridBagConstraints);

        chkDuneBuggy.setText("Dune Buggy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlChassisMods.add(chkDuneBuggy, gridBagConstraints);

        chkEnviroSealing.setText("Enviro (Vacuum) Sealing");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlChassisMods.add(chkEnviroSealing, gridBagConstraints);

        pnlExperimental.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Experimental Equipment"));

        chkArmoredMotive.setText("Armored Motive System");

        chkCommandConsole.setText("Command Console");

        chkEscapePod.setText("Combat Vehicle Escape Pod");

        chkMinesweeper.setText("Minesweeper");

        chkJetBooster.setText("VTOL Jet Booster");

        chkSupercharger.setText("Supercharger");
        chkSupercharger.setEnabled(false);
        chkSupercharger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSuperchargerActionPerformed(evt);
            }
        });

        lblSupercharger.setText("Install in:");
        lblSupercharger.setEnabled(false);

        cmbSCLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CT", "LT", "RT" }));
        cmbSCLoc.setEnabled(false);
        cmbSCLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSCLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlExperimentalLayout = new javax.swing.GroupLayout(pnlExperimental);
        pnlExperimental.setLayout(pnlExperimentalLayout);
        pnlExperimentalLayout.setHorizontalGroup(
            pnlExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExperimentalLayout.createSequentialGroup()
                .addGroup(pnlExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkArmoredMotive)
                    .addComponent(chkSupercharger)
                    .addGroup(pnlExperimentalLayout.createSequentialGroup()
                        .addComponent(lblSupercharger)
                        .addComponent(cmbSCLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkJetBooster)
                    .addComponent(chkMinesweeper)
                    .addComponent(chkCommandConsole)
                    .addComponent(chkEscapePod))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        pnlExperimentalLayout.setVerticalGroup(
            pnlExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExperimentalLayout.createSequentialGroup()
                .addComponent(chkArmoredMotive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSupercharger)
                .addGroup(pnlExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlExperimentalLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblSupercharger))
                    .addComponent(cmbSCLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCommandConsole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMinesweeper)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkJetBooster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEscapePod))
        );

        javax.swing.GroupLayout pnlBasicSetupLayout = new javax.swing.GroupLayout(pnlBasicSetup);
        pnlBasicSetup.setLayout(pnlBasicSetupLayout);
        pnlBasicSetupLayout.setHorizontalGroup(
            pnlBasicSetupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBasicSetupLayout.createSequentialGroup()
                .addGroup(pnlBasicSetupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChassisMods, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMovement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBasicSetupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlExperimental, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChassis, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBasicSetupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );
        pnlBasicSetupLayout.setVerticalGroup(
            pnlBasicSetupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBasicSetupLayout.createSequentialGroup()
                .addGroup(pnlBasicSetupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBasicSetupLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlMovement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(pnlChassisMods, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBasicSetupLayout.createSequentialGroup()
                        .addComponent(pnlSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(pnlInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBasicSetupLayout.createSequentialGroup()
                        .addComponent(pnlChassis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlExperimental, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(817, 817, 817))
        );

        tbpMainTabPane.addTab("Basic Setup", pnlBasicSetup);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Armor Locations"));

        pnlRightArmor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Right"));
        pnlRightArmor.setLayout(new java.awt.GridBagLayout());

        lblRightIntPts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRightIntPts.setText("00");
        lblRightIntPts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblRightIntPts.setMaximumSize(new java.awt.Dimension(45, 20));
        lblRightIntPts.setMinimumSize(new java.awt.Dimension(45, 20));
        lblRightIntPts.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlRightArmor.add(lblRightIntPts, gridBagConstraints);

        jLabel40.setText("Internal");
        pnlRightArmor.add(jLabel40, new java.awt.GridBagConstraints());

        jLabel46.setText("Armor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlRightArmor.add(jLabel46, gridBagConstraints);

        spnRightArmor.setMinimumSize(new java.awt.Dimension(45, 20));
        spnRightArmor.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlRightArmor.add(spnRightArmor, gridBagConstraints);

        pnlFrontArmor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Front"));
        pnlFrontArmor.setLayout(new java.awt.GridBagLayout());

        lblFrontIntPts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrontIntPts.setText("00");
        lblFrontIntPts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFrontIntPts.setMaximumSize(new java.awt.Dimension(45, 20));
        lblFrontIntPts.setMinimumSize(new java.awt.Dimension(45, 20));
        lblFrontIntPts.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlFrontArmor.add(lblFrontIntPts, gridBagConstraints);

        jLabel45.setText("Internal");
        pnlFrontArmor.add(jLabel45, new java.awt.GridBagConstraints());

        jLabel47.setText("Armor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlFrontArmor.add(jLabel47, gridBagConstraints);

        spnFrontArmor.setMinimumSize(new java.awt.Dimension(45, 20));
        spnFrontArmor.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlFrontArmor.add(spnFrontArmor, gridBagConstraints);

        pnlLeftArmor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Left"));
        pnlLeftArmor.setLayout(new java.awt.GridBagLayout());

        lblLeftIntPts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLeftIntPts.setText("00");
        lblLeftIntPts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLeftIntPts.setMaximumSize(new java.awt.Dimension(45, 20));
        lblLeftIntPts.setMinimumSize(new java.awt.Dimension(45, 20));
        lblLeftIntPts.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlLeftArmor.add(lblLeftIntPts, gridBagConstraints);

        jLabel41.setText("Internal");
        pnlLeftArmor.add(jLabel41, new java.awt.GridBagConstraints());

        jLabel48.setText("Armor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlLeftArmor.add(jLabel48, gridBagConstraints);

        spnLeftArmor.setMinimumSize(new java.awt.Dimension(45, 20));
        spnLeftArmor.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlLeftArmor.add(spnLeftArmor, gridBagConstraints);

        pnlRearArmor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rear"));
        pnlRearArmor.setLayout(new java.awt.GridBagLayout());

        lblRearIntPts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRearIntPts.setText("00");
        lblRearIntPts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblRearIntPts.setMaximumSize(new java.awt.Dimension(45, 20));
        lblRearIntPts.setMinimumSize(new java.awt.Dimension(45, 20));
        lblRearIntPts.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlRearArmor.add(lblRearIntPts, gridBagConstraints);

        jLabel44.setText("Internal");
        pnlRearArmor.add(jLabel44, new java.awt.GridBagConstraints());

        jLabel49.setText("Armor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlRearArmor.add(jLabel49, gridBagConstraints);

        spnRearArmor.setMinimumSize(new java.awt.Dimension(45, 20));
        spnRearArmor.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlRearArmor.add(spnRearArmor, gridBagConstraints);

        pnlTurretArmor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Turret"));
        pnlTurretArmor.setLayout(new java.awt.GridBagLayout());

        lblTurretIntPts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurretIntPts.setText("00");
        lblTurretIntPts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblTurretIntPts.setMaximumSize(new java.awt.Dimension(45, 20));
        lblTurretIntPts.setMinimumSize(new java.awt.Dimension(45, 20));
        lblTurretIntPts.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlTurretArmor.add(lblTurretIntPts, gridBagConstraints);

        jLabel42.setText("Internal");
        pnlTurretArmor.add(jLabel42, new java.awt.GridBagConstraints());

        jLabel50.setText("Armor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlTurretArmor.add(jLabel50, gridBagConstraints);

        spnTurretArmor.setMinimumSize(new java.awt.Dimension(45, 20));
        spnTurretArmor.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlTurretArmor.add(spnTurretArmor, gridBagConstraints);

        pnlRearTurretArmor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "R Turret"));
        pnlRearTurretArmor.setLayout(new java.awt.GridBagLayout());

        lblRearTurretIntPts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRearTurretIntPts.setText("00");
        lblRearTurretIntPts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblRearTurretIntPts.setMaximumSize(new java.awt.Dimension(45, 20));
        lblRearTurretIntPts.setMinimumSize(new java.awt.Dimension(45, 20));
        lblRearTurretIntPts.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlRearTurretArmor.add(lblRearTurretIntPts, gridBagConstraints);

        jLabel43.setText("Internal");
        pnlRearTurretArmor.add(jLabel43, new java.awt.GridBagConstraints());

        jLabel51.setText("Armor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlRearTurretArmor.add(jLabel51, gridBagConstraints);

        spnRearTurretArmor.setMinimumSize(new java.awt.Dimension(45, 20));
        spnRearTurretArmor.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlRearTurretArmor.add(spnRearTurretArmor, gridBagConstraints);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pnlRearArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlFrontArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLeftArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTurretArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRearTurretArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRightArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(pnlFrontArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRightArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLeftArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTurretArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRearTurretArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRearArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Armor Type"));

        jLabel52.setText("Armor Type:");

        cmbArmorType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Standard", "Industrial", "Commercial", "Ferro-Fibrous", "Light Ferro-Fibrous", "Heavy Ferro-Fibrous", "Vehicular Stealth" }));
        cmbArmorType.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbArmorType.setPreferredSize(new java.awt.Dimension(150, 20));

        chkBalanceLRArmor.setText("Balance Left/Right Armor");

        chkBalanceFRArmor.setText("Balance Front/Rear Armor");

        btnSetArmorTons.setText("Set Armor Tonnage");

        btnUseRemaining.setText("Use Remaining Tonnage");

        btnMaximize.setText("Maximize Armor");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel52)
                        .addGap(2, 2, 2)
                        .addComponent(cmbArmorType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnSetArmorTons, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnUseRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnMaximize, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkBalanceLRArmor)
                            .addComponent(chkBalanceFRArmor))))
                .addGap(10, 10, 10))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel52))
                    .addComponent(cmbArmorType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBalanceLRArmor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBalanceFRArmor)
                .addComponent(btnSetArmorTons)
                .addComponent(btnUseRemaining)
                .addComponent(btnMaximize))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Armor Information"));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel34.setText("Tons");
        jPanel8.add(jLabel34, new java.awt.GridBagConstraints());

        jLabel36.setText("Space");
        jPanel8.add(jLabel36, new java.awt.GridBagConstraints());

        lblArmorTotals.setText("999 of 999 Armor Points");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel8.add(lblArmorTotals, gridBagConstraints);

        lblArmorCoverage.setText("100.00% Coverage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel8.add(lblArmorCoverage, gridBagConstraints);

        txtArmorTons.setEditable(false);
        txtArmorTons.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtArmorTons.setText("000.00");
        txtArmorTons.setMinimumSize(new java.awt.Dimension(50, 20));
        txtArmorTons.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel8.add(txtArmorTons, gridBagConstraints);

        txtArmorSpace.setEditable(false);
        txtArmorSpace.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtArmorSpace.setText("00");
        txtArmorSpace.setMinimumSize(new java.awt.Dimension(40, 20));
        txtArmorSpace.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel8.add(txtArmorSpace, gridBagConstraints);

        lblArmorTonsWasted.setText("0.00 Tons Wasted");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel8.add(lblArmorTonsWasted, gridBagConstraints);

        lblArmorLeftInLot.setText("99 Points Left In This 1/2 Ton Lot");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel8.add(lblArmorLeftInLot, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(941, 941, 941))
        );

        tbpMainTabPane.addTab("Armor", jPanel2);

        tbpWeaponChooser.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        tbpWeaponChooser.setMaximumSize(new java.awt.Dimension(300, 300));
        tbpWeaponChooser.setMinimumSize(new java.awt.Dimension(300, 300));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setAlignmentX(0.0F);
        jSeparator5.setAlignmentY(0.0F);

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane8.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane8.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChooseBallistic.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChooseBallistic.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChooseBallistic.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChooseBallistic.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChooseBallistic.setPreferredSize(null);
        lstChooseBallistic.setVisibleRowCount(16);
        lstChooseBallistic.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChooseBallisticValueChanged(evt);
            }
        });
        MouseListener mlBallistic = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChooseBallistic.addMouseListener( mlBallistic );
        lstChooseBallistic.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane8.setViewportView(lstChooseBallistic);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setAlignmentX(0.0F);
        jSeparator6.setAlignmentY(0.0F);

        javax.swing.GroupLayout pnlBallisticLayout = new javax.swing.GroupLayout(pnlBallistic);
        pnlBallistic.setLayout(pnlBallisticLayout);
        pnlBallisticLayout.setHorizontalGroup(
            pnlBallisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBallisticLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBallisticLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(pnlBallisticLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlBallisticLayout.setVerticalGroup(
            pnlBallisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBallisticLayout.createSequentialGroup()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        tbpWeaponChooser.addTab("Ballistic", pnlBallistic);

        pnlEnergy.setLayout(new javax.swing.BoxLayout(pnlEnergy, javax.swing.BoxLayout.Y_AXIS));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setAlignmentX(0.0F);
        jSeparator7.setAlignmentY(0.0F);
        pnlEnergy.add(jSeparator7);

        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane9.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane9.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane9.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChooseEnergy.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChooseEnergy.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChooseEnergy.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChooseEnergy.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChooseEnergy.setPreferredSize(null);
        lstChooseEnergy.setVisibleRowCount(16);
        lstChooseEnergy.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChooseEnergyValueChanged(evt);
            }
        });
        MouseListener mlEnergy = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChooseEnergy.addMouseListener( mlEnergy );
        lstChooseEnergy.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane9.setViewportView(lstChooseEnergy);

        pnlEnergy.add(jScrollPane9);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator8.setAlignmentX(0.0F);
        jSeparator8.setAlignmentY(0.0F);
        pnlEnergy.add(jSeparator8);

        tbpWeaponChooser.addTab("Energy", pnlEnergy);

        pnlMissile.setLayout(new javax.swing.BoxLayout(pnlMissile, javax.swing.BoxLayout.Y_AXIS));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setAlignmentX(0.0F);
        jSeparator9.setAlignmentY(0.0F);
        pnlMissile.add(jSeparator9);

        jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane19.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane19.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane19.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane19.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChooseMissile.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChooseMissile.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChooseMissile.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChooseMissile.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChooseMissile.setPreferredSize(null);
        lstChooseMissile.setVisibleRowCount(16);
        lstChooseMissile.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChooseMissileValueChanged(evt);
            }
        });
        MouseListener mlMissile = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChooseMissile.addMouseListener( mlMissile );
        lstChooseMissile.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane19.setViewportView(lstChooseMissile);

        pnlMissile.add(jScrollPane19);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator10.setAlignmentX(0.0F);
        jSeparator10.setAlignmentY(0.0F);
        pnlMissile.add(jSeparator10);

        tbpWeaponChooser.addTab("Missile", pnlMissile);

        pnlPhysical.setLayout(new javax.swing.BoxLayout(pnlPhysical, javax.swing.BoxLayout.Y_AXIS));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator11.setAlignmentX(0.0F);
        jSeparator11.setAlignmentY(0.0F);
        pnlPhysical.add(jSeparator11);

        jScrollPane20.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane20.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane20.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane20.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane20.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChoosePhysical.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChoosePhysical.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChoosePhysical.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChoosePhysical.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChoosePhysical.setPreferredSize(null);
        lstChoosePhysical.setVisibleRowCount(16);
        lstChoosePhysical.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChoosePhysicalValueChanged(evt);
            }
        });
        MouseListener mlPhysical = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChoosePhysical.addMouseListener( mlPhysical );
        lstChoosePhysical.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane20.setViewportView(lstChoosePhysical);

        pnlPhysical.add(jScrollPane20);

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator12.setAlignmentX(0.0F);
        jSeparator12.setAlignmentY(0.0F);
        pnlPhysical.add(jSeparator12);

        tbpWeaponChooser.addTab("Physical", pnlPhysical);

        pnlEquipmentChooser.setLayout(new javax.swing.BoxLayout(pnlEquipmentChooser, javax.swing.BoxLayout.Y_AXIS));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator13.setAlignmentX(0.0F);
        jSeparator13.setAlignmentY(0.0F);
        pnlEquipmentChooser.add(jSeparator13);

        jScrollPane21.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane21.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane21.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane21.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane21.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChooseEquipment.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChooseEquipment.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChooseEquipment.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChooseEquipment.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChooseEquipment.setPreferredSize(null);
        lstChooseEquipment.setVisibleRowCount(16);
        lstChooseEquipment.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChooseEquipmentValueChanged(evt);
            }
        });
        MouseListener mlEquipment = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChooseEquipment.addMouseListener( mlEquipment );
        lstChooseEquipment.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane21.setViewportView(lstChooseEquipment);

        pnlEquipmentChooser.add(jScrollPane21);

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator14.setAlignmentX(0.0F);
        jSeparator14.setAlignmentY(0.0F);
        pnlEquipmentChooser.add(jSeparator14);

        tbpWeaponChooser.addTab("Equipment", pnlEquipmentChooser);

        pnlArtillery.setLayout(new javax.swing.BoxLayout(pnlArtillery, javax.swing.BoxLayout.Y_AXIS));

        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator18.setAlignmentX(0.0F);
        jSeparator18.setAlignmentY(0.0F);
        pnlArtillery.add(jSeparator18);

        jScrollPane24.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane24.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane24.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane24.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane24.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChooseArtillery.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChooseArtillery.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChooseArtillery.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChooseArtillery.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChooseArtillery.setPreferredSize(null);
        lstChooseArtillery.setVisibleRowCount(16);
        lstChooseArtillery.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChooseArtilleryValueChanged(evt);
            }
        });
        MouseListener mlArtillery = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChooseArtillery.addMouseListener( mlArtillery );
        lstChooseArtillery.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane24.setViewportView(lstChooseArtillery);

        pnlArtillery.add(jScrollPane24);

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator19.setAlignmentX(0.0F);
        jSeparator19.setAlignmentY(0.0F);
        pnlArtillery.add(jSeparator19);

        tbpWeaponChooser.addTab("Artillery", pnlArtillery);

        pnlAmmunition.setLayout(new javax.swing.BoxLayout(pnlAmmunition, javax.swing.BoxLayout.Y_AXIS));

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator15.setAlignmentX(0.0F);
        jSeparator15.setAlignmentY(0.0F);
        pnlAmmunition.add(jSeparator15);

        jScrollPane22.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane22.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane22.setMaximumSize(new java.awt.Dimension(200, 260));
        jScrollPane22.setMinimumSize(new java.awt.Dimension(200, 260));
        jScrollPane22.setPreferredSize(new java.awt.Dimension(200, 260));

        lstChooseAmmunition.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Placeholder" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstChooseAmmunition.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstChooseAmmunition.setMaximumSize(new java.awt.Dimension(180, 10000));
        lstChooseAmmunition.setMinimumSize(new java.awt.Dimension(180, 100));
        lstChooseAmmunition.setPreferredSize(null);
        lstChooseAmmunition.setVisibleRowCount(16);
        lstChooseAmmunition.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstChooseAmmunitionValueChanged(evt);
            }
        });
        MouseListener mlAmmo = new MouseAdapter() {
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 && e.getButton() == 1 ) {
                    btnAddEquipActionPerformed( null );
                }
            }
        };
        lstChooseAmmunition.addMouseListener( mlAmmo );
        lstChooseAmmunition.setCellRenderer( new saw.gui.EquipmentListRenderer( this ) );
        jScrollPane22.setViewportView(lstChooseAmmunition);

        pnlAmmunition.add(jScrollPane22);

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setAlignmentX(0.0F);
        jSeparator16.setAlignmentY(0.0F);
        pnlAmmunition.add(jSeparator16);

        tbpWeaponChooser.addTab("Ammunition", pnlAmmunition);

        pnlSpecials.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Specials"));
        pnlSpecials.setLayout(new java.awt.GridBagLayout());

        jLabel37.setText("Missile Guidance:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        pnlSpecials.add(jLabel37, gridBagConstraints);

        chkUseTC.setText("Targeting Computer");
        chkUseTC.setEnabled(false);
        chkUseTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUseTCActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 2, 0, 0);
        pnlSpecials.add(chkUseTC, gridBagConstraints);

        chkFCSAIV.setText("Use Artemis IV");
        chkFCSAIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFCSAIVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        pnlSpecials.add(chkFCSAIV, gridBagConstraints);

        chkFCSAV.setText("Use Artemis V");
        chkFCSAV.setEnabled(false);
        chkFCSAV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFCSAVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        pnlSpecials.add(chkFCSAV, gridBagConstraints);

        chkFCSApollo.setText("Use MRM Apollo");
        chkFCSApollo.setEnabled(false);
        chkFCSApollo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFCSApolloActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        pnlSpecials.add(chkFCSApollo, gridBagConstraints);

        chkClanCASE.setText("Use Clan CASE");
        chkClanCASE.setEnabled(false);
        chkClanCASE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkClanCASEActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 2, 0, 0);
        pnlSpecials.add(chkClanCASE, gridBagConstraints);

        pnlSelected.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Selected Equipment"));
        pnlSelected.setMaximumSize(new java.awt.Dimension(212, 286));
        pnlSelected.setMinimumSize(new java.awt.Dimension(212, 286));
        pnlSelected.setLayout(new javax.swing.BoxLayout(pnlSelected, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane23.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane23.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lstSelectedEquipment.setModel( new javax.swing.DefaultListModel()
        );
        lstSelectedEquipment.setMaximumSize(new java.awt.Dimension(180, 225));
        lstSelectedEquipment.setMinimumSize(new java.awt.Dimension(180, 225));
        lstSelectedEquipment.setPreferredSize(null);
        lstSelectedEquipment.setVisibleRowCount(16);
        lstSelectedEquipment.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstSelectedEquipmentValueChanged(evt);
            }
        });
        lstSelectedEquipment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstSelectedEquipmentKeyPressed(evt);
            }
        });
        jScrollPane23.setViewportView(lstSelectedEquipment);

        pnlSelected.add(jScrollPane23);

        pnlEquipInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Information"));
        pnlEquipInfo.setLayout(new java.awt.GridBagLayout());

        jLabel38.setText("Availability(AoW/SL)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        pnlEquipInfo.add(jLabel38, gridBagConstraints);

        jLabel39.setText("Availability (SW)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        pnlEquipInfo.add(jLabel39, gridBagConstraints);

        jLabel53.setText("Availability (CI)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        pnlEquipInfo.add(jLabel53, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoAVSL, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoAVSW, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoAVCI, gridBagConstraints);

        jLabel54.setText("Introduction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(jLabel54, gridBagConstraints);

        jLabel55.setText("Extinction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(jLabel55, gridBagConstraints);

        jLabel56.setText("Reintroduction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(jLabel56, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoIntro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoExtinct, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoReintro, gridBagConstraints);

        jLabel57.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 3);
        pnlEquipInfo.add(jLabel57, gridBagConstraints);

        jLabel58.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel58, gridBagConstraints);

        jLabel59.setText("Heat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel59, gridBagConstraints);

        jLabel60.setText("Damage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel60, gridBagConstraints);

        jLabel61.setText("Range");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel61, gridBagConstraints);

        lblInfoName.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        pnlEquipInfo.add(lblInfoName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoType, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoHeat, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoDamage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoRange, gridBagConstraints);

        jSeparator17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        pnlEquipInfo.add(jSeparator17, gridBagConstraints);

        jLabel62.setText("Ammo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel62, gridBagConstraints);

        jLabel63.setText("Tonnage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel63, gridBagConstraints);

        jLabel64.setText("Crits");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 3);
        pnlEquipInfo.add(jLabel64, gridBagConstraints);

        jLabel65.setText("Specials");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 0);
        pnlEquipInfo.add(jLabel65, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoAmmo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoTonnage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(lblInfoCrits, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        pnlEquipInfo.add(lblInfoSpecials, gridBagConstraints);

        jSeparator20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        pnlEquipInfo.add(jSeparator20, gridBagConstraints);

        jLabel66.setText("Cost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(jLabel66, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        pnlEquipInfo.add(lblInfoCost, gridBagConstraints);

        jLabel67.setText("BV");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(jLabel67, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        pnlEquipInfo.add(lblInfoBV, gridBagConstraints);

        jLabel68.setText("Mounting Restrictions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 3);
        pnlEquipInfo.add(jLabel68, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        pnlEquipInfo.add(lblInfoMountRestrict, gridBagConstraints);

        jLabel69.setText("Rules Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        pnlEquipInfo.add(jLabel69, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        pnlEquipInfo.add(lblInfoRulesLevel, gridBagConstraints);

        pnlControls.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Controls"));
        pnlControls.setLayout(new java.awt.GridBagLayout());

        btnRemoveEquip.setText("<<");
        btnRemoveEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveEquipActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlControls.add(btnRemoveEquip, gridBagConstraints);

        btnClearEquip.setText("Clear");
        btnClearEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearEquipActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlControls.add(btnClearEquip, gridBagConstraints);

        btnAddEquip.setText(">>");
        btnAddEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEquipActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        pnlControls.add(btnAddEquip, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tbpWeaponChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlSpecials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addComponent(pnlSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                    .addComponent(pnlEquipInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSpecials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(tbpWeaponChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 284, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEquipInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(844, 844, 844))
        );

        tbpMainTabPane.addTab("Equipment", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        tbpMainTabPane.addTab("Fluff", jPanel4);

        pnlBFStats.setBorder(javax.swing.BorderFactory.createTitledBorder("BattleForce Stats"));
        pnlBFStats.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel70.setText("MV");
        pnlBFStats.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel71.setText("S (+0)");
        pnlBFStats.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel72.setText("M (+2)");
        pnlBFStats.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel73.setText("L (+4)");
        pnlBFStats.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel74.setText("E (+6)");
        pnlBFStats.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabel75.setText("Wt.");
        pnlBFStats.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel76.setText("OV");
        pnlBFStats.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel77.setText("Armor:");
        pnlBFStats.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel78.setText("Structure:");
        pnlBFStats.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        jLabel79.setText("Special Abilities:");
        pnlBFStats.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        lblBFMV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFMV.setText("0");
        pnlBFStats.add(lblBFMV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 30, -1));

        lblBFWt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFWt.setText("1");
        pnlBFStats.add(lblBFWt, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 30, -1));

        lblBFOV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFOV.setText("0");
        pnlBFStats.add(lblBFOV, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 30, -1));

        lblBFExtreme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFExtreme.setText("0");
        pnlBFStats.add(lblBFExtreme, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 30, -1));

        lblBFShort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFShort.setText("0");
        pnlBFStats.add(lblBFShort, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 30, -1));

        lblBFMedium.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFMedium.setText("0");
        pnlBFStats.add(lblBFMedium, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 30, -1));

        lblBFLong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFLong.setText("0");
        pnlBFStats.add(lblBFLong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 30, -1));

        lblBFArmor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFArmor.setText("0");
        pnlBFStats.add(lblBFArmor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 30, -1));

        lblBFStructure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBFStructure.setText("0");
        pnlBFStats.add(lblBFStructure, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 30, -1));

        lblBFSA.setText("Placeholder");
        pnlBFStats.add(lblBFSA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 430, 20));

        jLabel80.setText("Points:");
        pnlBFStats.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        lblBFPoints.setText("0");
        pnlBFStats.add(lblBFPoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Conversion Steps"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextAreaBFConversion.setColumns(20);
        jTextAreaBFConversion.setEditable(false);
        jTextAreaBFConversion.setRows(5);
        jScrollPane14.setViewportView(jTextAreaBFConversion);

        jPanel10.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 660, 190));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBFStats, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(pnlBFStats, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbpMainTabPane.addTab("BattleForce", jPanel9);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(pnlInfoPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(tbpMainTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpMainTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RefreshSummary() {
        // refreshes the display completely using info from the mech.
        txtSumEngTons.setText( "" + CurVee.GetEngine().GetTonnage() );
//        txtSumConTons.setText( "" + CurVee.GetCockpit().GetTonnage() );
//        txtSumHSTons.setText( "" + CurVee.GetHeatSinks().GetTonnage() );
//        txtSumJJTons.setText( "" + CurVee.GetJumpJets().GetTonnage() );
//        txtSumArmTons.setText( "" + CurVee.GetArmor().GetTonnage() );
//        txtSumPATons.setText( "" + CurVee.GetLoadout().GetPowerAmplifier().GetTonnage() );
//        txtSumIntAV.setText( CurVee.GetIntStruc().GetAvailability().GetBestCombinedCode() );
//        txtSumEngAV.setText( CurVee.GetEngine().GetAvailability().GetBestCombinedCode() );
//        txtSumConAV.setText( CurVee.GetCockpit().GetAvailability().GetBestCombinedCode() );
//        txtSumHSAV.setText( CurVee.GetHeatSinks().GetAvailability().GetBestCombinedCode() );
//        txtSumJJAV.setText( CurVee.GetJumpJets().GetAvailability().GetBestCombinedCode() );
//        txtSumPAAV.setText( CurVee.GetLoadout().GetPowerAmplifier().GetAvailability().GetBestCombinedCode() );

        // added for the armor pane
        lblArmorTotals.setText( CurVee.GetArmor().GetArmorValue() + " of " + CurVee.GetArmor().GetMaxArmor() + " Armor Points" );
        lblArmorCoverage.setText( CurVee.GetArmor().GetCoverage() + "% Coverage" );
        lblArmorTonsWasted.setText( CurVee.GetArmor().GetWastedTonnage() + " Tons Wasted" );
        lblArmorLeftInLot.setText( CurVee.GetArmor().GetWastedAV() + " Points Left In This 1/2 Ton Lot" );

        // added for Battleforce pane
//        BattleForceStats bfs = new BattleForceStats(CurVee);
//
//        lblBFMV.setText( bfs.getMovement() );
//        lblBFWt.setText( bfs.getWeight() + "" );
//        lblBFArmor.setText( bfs.getArmor() + "" );
//        lblBFStructure.setText( bfs.getInternal() + "" );
//        lblBFPoints.setText("" + bfs.getPointValue() );
//
//        //int [] BFdmg = CurVee.GetBFDamage( bfs );
//        lblBFShort.setText("" + bfs.getShort() );
//        lblBFMedium.setText("" + bfs.getMedium() );
//        lblBFLong.setText("" + bfs.getLong() );
//        lblBFExtreme.setText("" + bfs.getExtreme() );
//        lblBFOV.setText("" + bfs.getOverheat() );
//
//        lblBFSA.setText( bfs.getAbilitiesString() );
//
//        jTextAreaBFConversion.setText( bfs.getBFConversionData() );
    }

    private void SolidifyVehicle() {
        // sets some of the basic vehicle information normally kept in the GUI and
        // prepares the vehicle for saving to file
        int year = 0;
        CurVee.setName( txtVehicleName.getText() );
        CurVee.setModel( txtModel.getText() );
        if( txtProdYear.getText().isEmpty() ) {
            switch( cmbEra.getSelectedIndex() ) {
            case AvailableCode.ERA_STAR_LEAGUE:
                CurVee.setYear( 2750, false );
                break;
            case AvailableCode.ERA_SUCCESSION:
                CurVee.setYear( 3025, false );
                break;
            case AvailableCode.ERA_CLAN_INVASION:
                CurVee.setYear( 3070, false );
                break;
            case AvailableCode.ERA_DARK_AGES:
                CurVee.setYear( 3132, false );
                break;
            }
        } else {
            try{
                year = Integer.parseInt( txtProdYear.getText() ) ;
                CurVee.setYear( year, true );
            } catch( NumberFormatException n ) {
                Media.Messager( this, "The production year is not a number." );
                tbpMainTabPane.setSelectedComponent( pnlBasicSetup );
                return;
            }
        }

        //CurVee.SetOverview( Overview.GetText() );
        //CurVee.SetCapabilities( Capabilities.GetText() );
        //CurVee.SetHistory( History.GetText() );
        //CurVee.SetDeployment( Deployment.GetText() );
        //CurVee.SetVariants( Variants.GetText() );
        //CurVee.SetNotables( Notables.GetText() );
        //CurVee.SetAdditional( Additional.GetText() );
        //CurVee.SetCompany( txtManufacturer.getText() );
        //CurVee.SetLocation( txtManufacturerLocation.getText() );
        //CurVee.SetEngineManufacturer( txtEngineManufacturer.getText() );
        //CurVee.SetArmorModel( txtArmorModel.getText() );
        //CurVee.SetChassisModel( txtChassisModel.getText() );
        //if( CurVee.GetJumpJets().GetNumJJ() > 0 ) {
        //    CurVee.SetJJModel( txtJJModel.getText() );
        //}
        //CurVee.SetCommSystem( txtCommSystem.getText() );
        //CurVee.SetTandTSystem( txtTNTSystem.getText() );
        CurVee.setSource( txtSource.getText() );
    }

    private void BuildArmorSelector() {
        // builds the armor selection box
        Vector list = new Vector();

        // get the armor states and, for each that matches our criteria, add it
        // to the selector list
        ifState[] check = CurVee.GetArmor().GetStates();
        for( int i = 0; i < check.length; i++ ) {
            if( CommonTools.IsAllowed( check[i].GetAvailability(), CurVee ) ) {
                list.add( BuildLookupName( check[i] ) );
            }
        }

        // turn the vector into a string array
        String[] temp = new String[list.size()];
        for( int i = 0; i < list.size(); i++ ) {
            temp[i] = (String) list.get(i);
        }

        // now set the armor chooser
        cmbArmorType.setModel( new javax.swing.DefaultComboBoxModel( temp ) );
    }

    private void BuildTechBaseSelector() {
        switch( CurVee.GetEra() ) {
            case AvailableCode.ERA_STAR_LEAGUE:
                cmbTechBase.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "Inner Sphere" } ) );
                break;
            default:
                if( CurVee.GetRulesLevel() >= AvailableCode.RULES_EXPERIMENTAL ) {
                    cmbTechBase.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "Inner Sphere", "Clan", "Mixed" } ) );
                } else if( CurVee.GetRulesLevel() == AvailableCode.RULES_INTRODUCTORY ) {
                    cmbTechBase.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "Inner Sphere" } ) );
                } else {
                    cmbTechBase.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "Inner Sphere", "Clan" } ) );
                }
                break;
        }
        try {
            cmbTechBase.setSelectedIndex( CurVee.GetTechbase() );
        } catch( Exception e ) {
            Media.Messager( "Could not set the Techbase due to changes.\nReverting to Inner Sphere." );
            cmbTechBase.setSelectedIndex( 0 );
        }
    }

    private void BuildEngineSelector() {
        // builds the engine selection box
        Vector list = new Vector();

        // get the engine states and, for each that matches our criteria, add it
        // to the selector list
        ifState[] check = CurVee.GetEngine().GetStates();
        for( int i = 0; i < check.length; i++ ) {
            if( CommonTools.IsAllowed( check[i].GetAvailability(), CurVee ) ) {
                list.add( BuildLookupName( check[i] ) );
            }
        }

        // turn the vector into a string array
        String[] temp = new String[list.size()];
        for( int i = 0; i < list.size(); i++ ) {
            temp[i] = (String) list.get(i);
        }

        // now set the engine chooser
        cmbEngineType.setModel( new javax.swing.DefaultComboBoxModel( temp ) );
    }

    private void FixArmorSpinners() {
        // fixes the armor spinners to match the new tonnage / motive type
        CVArmor a = CurVee.GetArmor();
        spnFrontArmor.setModel( new javax.swing.SpinnerNumberModel( a.GetLocationArmor( LocationIndex.CV_LOC_FRONT ), 0, a.GetLocationMax( LocationIndex.CV_LOC_FRONT ), 1) );
        spnLeftArmor.setModel( new javax.swing.SpinnerNumberModel( a.GetLocationArmor( LocationIndex.CV_LOC_LEFT ), 0, a.GetLocationMax( LocationIndex.CV_LOC_LEFT), 1) );
        spnRightArmor.setModel( new javax.swing.SpinnerNumberModel( a.GetLocationArmor( LocationIndex.CV_LOC_RIGHT ), 0, a.GetLocationMax( LocationIndex.CV_LOC_RIGHT), 1) );
        spnRearArmor.setModel( new javax.swing.SpinnerNumberModel( a.GetLocationArmor( LocationIndex.CV_LOC_REAR ), 0, a.GetLocationMax( LocationIndex.CV_LOC_REAR), 1) );
        spnTurretArmor.setModel( new javax.swing.SpinnerNumberModel( a.GetLocationArmor( LocationIndex.CV_LOC_TURRET1 ), 0, a.GetLocationMax( LocationIndex.CV_LOC_TURRET1), 1) );
        spnRearTurretArmor.setModel( new javax.swing.SpinnerNumberModel( a.GetLocationArmor( LocationIndex.CV_LOC_TURRET2 ), 0, a.GetLocationMax( LocationIndex.CV_LOC_TURRET2), 1) );
    }

    public String BuildLookupName( ifState s ) {
        String retval = s.LookupName();
        if( CurVee.GetLoadout().GetTechBase() == AvailableCode.TECH_BOTH ) {
            if( s.HasCounterpart() ) {
                if( s.GetAvailability().GetTechBase() == AvailableCode.TECH_CLAN ) {
                    return "(CL) " + retval;
                } else {
                    return "(IS) " + retval;
                }
            } else {
                return retval;
            }
        } else {
            return retval;
        }
    }

    public void FixTonnageSpinner( int MaximumTonnage ) {
        int CurVal = Integer.parseInt(spnTonnage.getValue().toString());
        if ( CurVal > MaximumTonnage ) CurVal = MaximumTonnage;
        spnTonnage.setModel( new javax.swing.SpinnerNumberModel(CurVal, 1, MaximumTonnage, 1) );
    }

    private void cmbRulesLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRulesLevelActionPerformed
        int NewLevel = cmbRulesLevel.getSelectedIndex();
        int OldLevel = CurVee.GetLoadout().GetRulesLevel();
        int OldType = cmbMotiveType.getSelectedIndex();
        int OldTech = CurVee.GetTechbase();

        if( OldLevel == NewLevel ) {
            // we're already at the correct rules level.
            return;
        }

        // do we have an OmniVee?
        if( CurVee.IsOmni() ) {
            // see if we can set to the new rules level.
            if( CurVee.GetLoadout().SetRulesLevel( NewLevel ) ) {
                // we can.
                if( OldLevel > NewLevel ) {
                    CurVee.GetLoadout().FlushIllegal();
                }
                BuildTechBaseSelector();
                cmbTechBase.setSelectedIndex( CurVee.GetLoadout().GetTechBase() );
                //RefreshEquipment();
                //RecalcEquipment();
            } else {
                // can't.  reset to the default rules level and scold the user
                Media.Messager( this, "You cannot set an OmniVee's loadout to a Rules Level\nlower than it's chassis' Rules Level." );
                cmbRulesLevel.setSelectedIndex( CurVee.GetLoadout().GetRulesLevel() );
                return;
            }
        } else {
            CurVee.SetRulesLevel( NewLevel );
            CheckTonnage( true );

            // get the currently chosen selections
            SaveSelections();
            BuildTechBaseSelector();
            if( OldTech >= cmbTechBase.getItemCount() ) {
                // ooooh fun, we can't set it correctly.
                switch( OldTech ) {
                    case AvailableCode.TECH_INNER_SPHERE:
                        // WTF???
                        System.err.println( "Fatal Error when reseting techbase, Inner Sphere not available." );
                        break;
                    default:
                        // set it to Inner Sphere
                        cmbTechBase.setSelectedIndex( 0 );
                        //cmbTechBaseActionPerformed( null );
                        break;
                }
            }

            // since you can only ever change the rules level when not restricted,
            // we're not doing it here.  Pass in default values.
            //CurMech.GetLoadout().FlushIllegal( CurMech.GetEra(), 0, false );
            CurVee.GetLoadout().FlushIllegal();

            // refresh all the combo boxes.
            BuildEngineSelector();
            BuildArmorSelector();
            //FixWalkMPSpinner();
            FixJJSpinnerModel();
            //RefreshEquipment();

            // now reset the combo boxes to the closest choices we previously selected
            LoadSelections();

            //RecalcEngine();
            //RecalcIntStruc();
            //RecalcHeatSinks();
            //RecalcArmor();
            //RecalcEquipment();
        }

        // now refresh the information panes
        RefreshSummary();
        RefreshInfoPane();
        //SetWeaponChoosers();
        //ResetAmmo();
}//GEN-LAST:event_cmbRulesLevelActionPerformed
    // check the tonnage to see if it's legal and acts accordingly
    public void CheckTonnage( boolean RulesChange ) {
        if( CurVee.GetTonnage() < 1 ) {
            spnTonnage.setValue(1);
        }
        
        if ( CurVee.GetTonnage() >= CurVee.GetMaxTonnage() ) {
            spnTonnage.setValue(CurVee.GetMaxTonnage());
        }
    }

    private void SaveSelections() {
        // saves the current GUI selections
        Selections[0] = BuildLookupName( CurVee.GetEngine().GetCurrentState() );
        Selections[1] = BuildLookupName( CurVee.GetArmor().GetCurrentState() );
    }

    private void LoadSelections() {
        // sets the current selections to the last saved selections or to the
        // default selections.  We'll do some validation here as well.
        cmbEngineType.setSelectedItem( Selections[0] );
        cmbArmorType.setSelectedItem( Selections[1] );
    }

    private void cmbMotiveTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMotiveTypeActionPerformed
        switch ( cmbMotiveType.getSelectedIndex() ) {
            case 0:      //Hovercraft
                CurVee.SetHover();
                break;
            case 1:     //Naval (Displacement)
                CurVee.SetDisplacement();
                break;
            case 2:     //Naval (Hydrofoil)
                CurVee.SetHydrofoil();
                break;
            case 3:     //Naval (Submarine)
                CurVee.SetSubmarine();
                break;
            case 4:     //Tracked
                CurVee.SetTracked();
                break;
            case 5:     //VTOL
                CurVee.setVTOL();
                break;
            case 6:     //Wheeled
                CurVee.SetWheeled();
                break;
            case 7:
                CurVee.SetWiGE();
                break;
            default:
                CurVee.SetHover();
                break;
        }
        FixTonnageSpinner(CurVee.GetMaxTonnage());
}//GEN-LAST:event_cmbMotiveTypeActionPerformed

    private void chkSuperchargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSuperchargerActionPerformed
 
}//GEN-LAST:event_chkSuperchargerActionPerformed

    private void cmbSCLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSCLocActionPerformed
  
}//GEN-LAST:event_cmbSCLocActionPerformed

    private void lstChooseBallisticValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChooseBallisticValueChanged

}//GEN-LAST:event_lstChooseBallisticValueChanged

    private void lstChooseEnergyValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChooseEnergyValueChanged

}//GEN-LAST:event_lstChooseEnergyValueChanged

    private void lstChooseMissileValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChooseMissileValueChanged

}//GEN-LAST:event_lstChooseMissileValueChanged

    private void lstChoosePhysicalValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChoosePhysicalValueChanged

}//GEN-LAST:event_lstChoosePhysicalValueChanged

    private void lstChooseEquipmentValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChooseEquipmentValueChanged

}//GEN-LAST:event_lstChooseEquipmentValueChanged

    private void lstChooseArtilleryValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChooseArtilleryValueChanged

}//GEN-LAST:event_lstChooseArtilleryValueChanged

    private void lstChooseAmmunitionValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChooseAmmunitionValueChanged

}//GEN-LAST:event_lstChooseAmmunitionValueChanged

    private void chkUseTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUseTCActionPerformed
 
}//GEN-LAST:event_chkUseTCActionPerformed

    private void chkFCSAIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFCSAIVActionPerformed
 
}//GEN-LAST:event_chkFCSAIVActionPerformed

    private void chkFCSAVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFCSAVActionPerformed
   
}//GEN-LAST:event_chkFCSAVActionPerformed

    private void chkFCSApolloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFCSApolloActionPerformed
 
}//GEN-LAST:event_chkFCSApolloActionPerformed

    private void chkClanCASEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkClanCASEActionPerformed
   
}//GEN-LAST:event_chkClanCASEActionPerformed

    private void lstSelectedEquipmentValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstSelectedEquipmentValueChanged
      
}//GEN-LAST:event_lstSelectedEquipmentValueChanged

    private void lstSelectedEquipmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstSelectedEquipmentKeyPressed
        
}//GEN-LAST:event_lstSelectedEquipmentKeyPressed

    private void btnRemoveEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveEquipActionPerformed
       
}//GEN-LAST:event_btnRemoveEquipActionPerformed

    private void btnClearEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearEquipActionPerformed
      
}//GEN-LAST:event_btnClearEquipActionPerformed

    private void btnAddEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEquipActionPerformed

}//GEN-LAST:event_btnAddEquipActionPerformed

    private void btnPostToS7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostToS7ActionPerformed
       
}//GEN-LAST:event_btnPostToS7ActionPerformed

    private void btnAddToForceListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToForceListActionPerformed

}//GEN-LAST:event_btnAddToForceListActionPerformed

    private void btnForceListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForceListActionPerformed

}//GEN-LAST:event_btnForceListActionPerformed

    private void spnCruiseMPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCruiseMPStateChanged
// see what changed and perform the appropriate action
        javax.swing.SpinnerNumberModel n = (SpinnerNumberModel) spnCruiseMP.getModel();
        javax.swing.JComponent editor = spnCruiseMP.getEditor();
        javax.swing.JFormattedTextField tf = ((javax.swing.JSpinner.DefaultEditor)editor).getTextField();

        n.setMinimum(1);
        // get the value from the text box, if it's valid.
        try {
            spnCruiseMP.commitEdit();
        } catch ( java.text.ParseException pe ) {
            // Edited value is invalid, spinner.getValue() will return
            // the last valid value, you could revert the spinner to show that:
            if (editor instanceof javax.swing.JSpinner.DefaultEditor) {
                tf.setValue(spnCruiseMP.getValue());
            }
            return;
        }
        try {
            // the commitedit worked, so set the engine rating and report the running mp
            CurVee.setCruiseMP( n.getNumber().intValue() );
        } catch( Exception e ) {
            Media.Messager( e.getMessage() );
            spnCruiseMP.setValue( spnCruiseMP.getPreviousValue() );
        }
        lblFlankMP.setText( "" + CurVee.getFlankMP() );

        // when the walking mp changes, we also have to change the jump mp
        // spinner model and recalculate the heat sinks
        FixJJSpinnerModel();
        //CurVee.GetHeatSinks().ReCalculate();
        //CurVee.GetLoadout().UnallocateFuelTanks();

        // now refresh the information panes
        RefreshSummary();
        RefreshInfoPane();
    }//GEN-LAST:event_spnCruiseMPStateChanged

    private void spnTonnageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spnTonnageFocusGained

    }//GEN-LAST:event_spnTonnageFocusGained

    private void FixJJSpinnerModel() {
        // since the jump jet spinner model changes every time the walking mp
        // changes, here is a quick little routine to do it without extra fuss.

        int min = 0;
        int max = 0;
        int current = 0;

//        if( CurVee.IsOmni() ) {
//            min = CurVee.GetJumpJets().GetBaseLoadoutNumJJ();
//        }

        max = CurVee.getCruiseMP();

//        current = CurVee.GetJumpJets().GetNumJJ();

        // is the number of jump jets greater than the maximum allowed?
//        if( current > max ) {
//            for( ; current > max; current-- ) {
//                CurVee.GetJumpJets().DecrementNumJJ();
//            }
//        }

        // is the number of jump jet less than the minimum?
//        if( current < min ) {
//            for( ; current < min; current++ ) {
//                CurVee.GetJumpJets().IncrementNumJJ();
//            }
//        }

        // see if we need to enable the jump jet manufacturer field
//        if( CurVee.GetJumpJets().GetNumJJ() > 0 ) {
//            // enable the field
//            txtJJModel.setEnabled( true );
//        } else {
//            // disable it, but don't clear it
//            txtJJModel.setEnabled( false );
//        }

        spnJumpMP.setModel( new javax.swing.SpinnerNumberModel( current, min, max, 1) );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEquip;
    private javax.swing.JButton btnAddToForceList;
    private javax.swing.JButton btnClearEquip;
    private javax.swing.JButton btnForceList;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnPostToS7;
    private javax.swing.JButton btnRemoveEquip;
    private javax.swing.JButton btnSetArmorTons;
    private javax.swing.JButton btnUseRemaining;
    private javax.swing.JCheckBox chkArmoredMotive;
    private javax.swing.JCheckBox chkBalanceFRArmor;
    private javax.swing.JCheckBox chkBalanceLRArmor;
    private javax.swing.JCheckBox chkClanCASE;
    private javax.swing.JCheckBox chkCommandConsole;
    private javax.swing.JCheckBox chkDuneBuggy;
    private javax.swing.JCheckBox chkEnviroSealing;
    private javax.swing.JCheckBox chkEscapePod;
    private javax.swing.JCheckBox chkFCSAIV;
    private javax.swing.JCheckBox chkFCSAV;
    private javax.swing.JCheckBox chkFCSApollo;
    private javax.swing.JCheckBox chkFlotationHull;
    private javax.swing.JCheckBox chkFullAmph;
    private javax.swing.JCheckBox chkJetBooster;
    private javax.swing.JCheckBox chkLimitedAmph;
    private javax.swing.JCheckBox chkMinesweeper;
    private javax.swing.JCheckBox chkOmniVee;
    private javax.swing.JCheckBox chkSupercharger;
    private javax.swing.JCheckBox chkTrailer;
    private javax.swing.JCheckBox chkUseTC;
    private javax.swing.JCheckBox chkYearRestrict;
    private javax.swing.JComboBox cmbArmorType;
    private javax.swing.JComboBox cmbEngineType;
    private javax.swing.JComboBox cmbEra;
    private javax.swing.JComboBox cmbMotiveType;
    private javax.swing.JComboBox cmbRulesLevel;
    private javax.swing.JComboBox cmbSCLoc;
    private javax.swing.JComboBox cmbTechBase;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JToolBar.Separator jSeparator25;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextAreaBFConversion;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblArmorCoverage;
    private javax.swing.JLabel lblArmorLeftInLot;
    private javax.swing.JLabel lblArmorTonsWasted;
    private javax.swing.JLabel lblArmorTotals;
    private javax.swing.JLabel lblBFArmor;
    private javax.swing.JLabel lblBFExtreme;
    private javax.swing.JLabel lblBFLong;
    private javax.swing.JLabel lblBFMV;
    private javax.swing.JLabel lblBFMedium;
    private javax.swing.JLabel lblBFOV;
    private javax.swing.JLabel lblBFPoints;
    private javax.swing.JLabel lblBFSA;
    private javax.swing.JLabel lblBFShort;
    private javax.swing.JLabel lblBFStructure;
    private javax.swing.JLabel lblBFWt;
    private javax.swing.JLabel lblBaseEngineRating;
    private javax.swing.JLabel lblEraYears;
    private javax.swing.JLabel lblFinalEngineRating;
    private javax.swing.JLabel lblFlankMP;
    private javax.swing.JLabel lblFreeHeatSinks;
    private javax.swing.JLabel lblFrontIntPts;
    private javax.swing.JLabel lblInfoAVCI;
    private javax.swing.JLabel lblInfoAVSL;
    private javax.swing.JLabel lblInfoAVSW;
    private javax.swing.JLabel lblInfoAmmo;
    private javax.swing.JLabel lblInfoBV;
    private javax.swing.JLabel lblInfoCost;
    private javax.swing.JLabel lblInfoCrits;
    private javax.swing.JLabel lblInfoDamage;
    private javax.swing.JLabel lblInfoExtinct;
    private javax.swing.JLabel lblInfoHeat;
    private javax.swing.JLabel lblInfoIntro;
    private javax.swing.JLabel lblInfoMountRestrict;
    private javax.swing.JLabel lblInfoName;
    private javax.swing.JLabel lblInfoRange;
    private javax.swing.JLabel lblInfoReintro;
    private javax.swing.JLabel lblInfoRulesLevel;
    private javax.swing.JLabel lblInfoSpecials;
    private javax.swing.JLabel lblInfoTonnage;
    private javax.swing.JLabel lblInfoType;
    private javax.swing.JLabel lblLeftIntPts;
    private javax.swing.JLabel lblMinEngineTons;
    private javax.swing.JLabel lblNumCrew;
    private javax.swing.JLabel lblRearIntPts;
    private javax.swing.JLabel lblRearTurretIntPts;
    private javax.swing.JLabel lblRightIntPts;
    private javax.swing.JLabel lblSupensionFacter;
    private javax.swing.JLabel lblSupercharger;
    private javax.swing.JLabel lblTurretIntPts;
    private javax.swing.JLabel lblVeeClass;
    private javax.swing.JList lstChooseAmmunition;
    private javax.swing.JList lstChooseArtillery;
    private javax.swing.JList lstChooseBallistic;
    private javax.swing.JList lstChooseEnergy;
    private javax.swing.JList lstChooseEquipment;
    private javax.swing.JList lstChooseMissile;
    private javax.swing.JList lstChoosePhysical;
    private javax.swing.JList lstSelectedEquipment;
    private javax.swing.JPanel pnlAmmunition;
    private javax.swing.JPanel pnlArtillery;
    private javax.swing.JPanel pnlBFStats;
    private javax.swing.JPanel pnlBallistic;
    private javax.swing.JPanel pnlBasicSetup;
    private javax.swing.JPanel pnlChassis;
    private javax.swing.JPanel pnlChassisMods;
    private javax.swing.JPanel pnlControls;
    private javax.swing.JPanel pnlEnergy;
    private javax.swing.JPanel pnlEquipInfo;
    private javax.swing.JPanel pnlEquipmentChooser;
    private javax.swing.JPanel pnlExperimental;
    private javax.swing.JPanel pnlFrontArmor;
    private javax.swing.JPanel pnlInfoPane;
    private javax.swing.JPanel pnlInformation;
    private javax.swing.JPanel pnlLeftArmor;
    private javax.swing.JPanel pnlMissile;
    private javax.swing.JPanel pnlMovement;
    private javax.swing.JPanel pnlPhysical;
    private javax.swing.JPanel pnlRearArmor;
    private javax.swing.JPanel pnlRearTurretArmor;
    private javax.swing.JPanel pnlRightArmor;
    private javax.swing.JPanel pnlSelected;
    private javax.swing.JPanel pnlSpecials;
    private javax.swing.JPanel pnlSummary;
    private javax.swing.JPanel pnlTurretArmor;
    private javax.swing.JSpinner spnCruiseMP;
    private javax.swing.JSpinner spnFrontArmor;
    private javax.swing.JSpinner spnJumpMP;
    private javax.swing.JSpinner spnLeftArmor;
    private javax.swing.JSpinner spnRearArmor;
    private javax.swing.JSpinner spnRearTurretArmor;
    private javax.swing.JSpinner spnRightArmor;
    private javax.swing.JSpinner spnTonnage;
    private javax.swing.JSpinner spnTurretArmor;
    private javax.swing.JTabbedPane tbpMainTabPane;
    private javax.swing.JTabbedPane tbpWeaponChooser;
    private javax.swing.JTextField txtArmorSpace;
    private javax.swing.JTextField txtArmorTons;
    private javax.swing.JTextField txtInfoBattleValue;
    private javax.swing.JTextField txtInfoCost;
    private javax.swing.JTextField txtInfoFreeCrits;
    private javax.swing.JTextField txtInfoFreeTons;
    private javax.swing.JTextField txtInfoTonnage;
    private javax.swing.JTextField txtInfoUnplaced;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtProdYear;
    private javax.swing.JTextField txtSource;
    private javax.swing.JTextField txtSumArmAV;
    private javax.swing.JTextField txtSumArmSpace;
    private javax.swing.JTextField txtSumArmTons;
    private javax.swing.JTextField txtSumConAV;
    private javax.swing.JTextField txtSumConTons;
    private javax.swing.JTextField txtSumEngAV;
    private javax.swing.JTextField txtSumEngSpace;
    private javax.swing.JTextField txtSumEngTons;
    private javax.swing.JTextField txtSumHSAV;
    private javax.swing.JTextField txtSumHSTons;
    private javax.swing.JTextField txtSumIntAV;
    private javax.swing.JTextField txtSumIntTons;
    private javax.swing.JTextField txtSumJJAV;
    private javax.swing.JTextField txtSumJJSpace;
    private javax.swing.JTextField txtSumJJTons;
    private javax.swing.JTextField txtSumLifAV;
    private javax.swing.JTextField txtSumLifTons;
    private javax.swing.JTextField txtSumPAAV;
    private javax.swing.JTextField txtSumPATons;
    private javax.swing.JTextField txtSumRTuAV;
    private javax.swing.JTextField txtSumRTuTons;
    private javax.swing.JTextField txtSumSpnAV;
    private javax.swing.JTextField txtSumSpnTons;
    private javax.swing.JTextField txtSumTurAV;
    private javax.swing.JTextField txtSumTurTons;
    private javax.swing.JTextField txtVehicleName;
    // End of variables declaration//GEN-END:variables

}
