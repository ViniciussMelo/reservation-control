package com.example.prova01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import util.Shared;

public class SecondaryActivity extends AppCompatActivity {

    private Button btnR1, btnR2, btnR3, btnR4, btnR5, btnR6, btnR7, btnR8, btnR9, btnFreeTable, btnReserveAllTables, btnChangeReservedColor, btnChangeNotReservedColor;
    private EditText edtTableNumber;
    private LinearLayout linearTable1, linearTable2, linearTable3, linearTable4, linearTable5, linearTable6, linearTable7, linearTable8, linearTable9;
    private Shared shared;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        try {
            shared = new Shared(SecondaryActivity.this);

            btnR1 = findViewById(R.id.btnR1);
            btnR2 = findViewById(R.id.btnR2);
            btnR3 = findViewById(R.id.btnR3);
            btnR4 = findViewById(R.id.btnR4);
            btnR5 = findViewById(R.id.btnR5);
            btnR6 = findViewById(R.id.btnR6);
            btnR7 = findViewById(R.id.btnR7);
            btnR8 = findViewById(R.id.btnR8);
            btnR9 = findViewById(R.id.btnR9);
            btnFreeTable = findViewById(R.id.btnFreeTable);
            btnReserveAllTables = findViewById(R.id.btnReserveAllTables);
            btnChangeReservedColor = findViewById(R.id.btnChangeReservedColor);
            btnChangeNotReservedColor = findViewById(R.id.btnChangeNotReservedColor);

            linearTable1 = findViewById(R.id.linearTable1);
            linearTable2 = findViewById(R.id.linearTable2);
            linearTable3 = findViewById(R.id.linearTable3);
            linearTable4 = findViewById(R.id.linearTable4);
            linearTable5 = findViewById(R.id.linearTable5);
            linearTable6 = findViewById(R.id.linearTable6);
            linearTable7 = findViewById(R.id.linearTable7);
            linearTable8 = findViewById(R.id.linearTable8);
            linearTable9 = findViewById(R.id.linearTable9);

            edtTableNumber = findViewById(R.id.edtTableNumber);

            addButtonEvent(btnR1, linearTable1, shared.KEY_TABLE1);
            addButtonEvent(btnR2, linearTable2, shared.KEY_TABLE2);
            addButtonEvent(btnR3, linearTable3, shared.KEY_TABLE3);
            addButtonEvent(btnR4, linearTable4, shared.KEY_TABLE4);
            addButtonEvent(btnR5, linearTable5, shared.KEY_TABLE5);
            addButtonEvent(btnR6, linearTable6, shared.KEY_TABLE6);
            addButtonEvent(btnR7, linearTable7, shared.KEY_TABLE7);
            addButtonEvent(btnR8, linearTable8, shared.KEY_TABLE8);
            addButtonEvent(btnR9, linearTable9, shared.KEY_TABLE9);
            addButtonEventFreeTable(btnFreeTable, edtTableNumber);
            addButtonEventReserveAllTables(btnReserveAllTables);
            addButtonEventChangeReservedColor(btnChangeReservedColor, true);
            addButtonEventChangeReservedColor(btnChangeNotReservedColor, false);

            verifyTables();
        } catch (Exception ex1) {
            Toast.makeText(SecondaryActivity.this, "Internal error: "+ex1.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void addButtonEvent(Button btn, LinearLayout linearTable, String sharedKey) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTableAlreadyReserved(sharedKey)) {
                    Toast.makeText(SecondaryActivity.this, "Table already reserved!", Toast.LENGTH_LONG).show();
                    return;
                }

                reserveTable(btn, linearTable, sharedKey);
            }
        });
    }

    private void reserveTable(Button btn, LinearLayout linearTable, String sharedKey) {
        changeTableColor(btn, linearTable);
        shared.put(sharedKey, true);
    }

    private boolean isTableAlreadyReserved(String sharedKey) {
        Boolean isTableAlreadyReserved = shared.getBoolean(sharedKey);

        return isTableAlreadyReserved;
    }

    private void changeTableColor(Button btn, LinearLayout linearTable) {
        btn.setEnabled(false);
        btn.setBackgroundColor(getResources().getColor(R.color.gray));

        String color = shared.getString(shared.KEY_RESERVED_TABLES);
        if (color == null) color = "RED";
        int backGroundColor = getColor(color);
        linearTable.setBackgroundColor(getResources().getColor(backGroundColor));
    }

    private void verifyTables() {
        verifyTable(btnR1, linearTable1, shared.KEY_TABLE1);
        verifyTable(btnR2, linearTable2, shared.KEY_TABLE2);
        verifyTable(btnR3, linearTable3, shared.KEY_TABLE3);
        verifyTable(btnR4, linearTable4, shared.KEY_TABLE4);
        verifyTable(btnR5, linearTable5, shared.KEY_TABLE5);
        verifyTable(btnR6, linearTable6, shared.KEY_TABLE6);
        verifyTable(btnR7, linearTable7, shared.KEY_TABLE7);
        verifyTable(btnR8, linearTable8, shared.KEY_TABLE8);
        verifyTable(btnR9, linearTable9, shared.KEY_TABLE9);
    }

    private void verifyTable(Button btn, LinearLayout linearTable, String sharedKey) {
        if (isTableAlreadyReserved(sharedKey)) {
            changeTableColor(btn, linearTable);
        } else {
            freeTableColor(btn, linearTable);
        }
    }

    private void addButtonEventFreeTable(Button btn, EditText edtTableNumber) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableNumber = edtTableNumber.getText().toString();

                try {
                    int number = Integer.parseInt(tableNumber);

                    freeTable(number);
                } catch (Error ex) {
                    Toast.makeText(SecondaryActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(SecondaryActivity.this, "Invalid option!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void freeTable(int number) {
        String sharedKey;
        Button btn;
        LinearLayout linearTable;

        switch (number) {
            case 1:
                btn = btnR1;
                linearTable = linearTable1;
                sharedKey = shared.KEY_TABLE1;
                break;
            case 2:
                btn = btnR2;
                linearTable = linearTable2;
                sharedKey = shared.KEY_TABLE2;
                break;
            case 3:
                btn = btnR3;
                linearTable = linearTable3;
                sharedKey = shared.KEY_TABLE3;
                break;
            case 4:
                btn = btnR4;
                linearTable = linearTable4;
                sharedKey = shared.KEY_TABLE4;
                break;
            case 5:
                btn = btnR5;
                linearTable = linearTable5;
                sharedKey = shared.KEY_TABLE5;
                break;
            case 6:
                btn = btnR6;
                linearTable = linearTable6;
                sharedKey = shared.KEY_TABLE6;
                break;
            case 7:
                btn = btnR7;
                linearTable = linearTable7;
                sharedKey = shared.KEY_TABLE7;
                break;
            case 8:
                btn = btnR8;
                linearTable = linearTable8;
                sharedKey = shared.KEY_TABLE8;
                break;
            case 9:
                btn = btnR9;
                linearTable = linearTable9;
                sharedKey = shared.KEY_TABLE9;
                break;
            default:
                throw new Error("Invalid table option!");
        }

        if (!isTableAlreadyReserved(sharedKey)) {
            throw new Error("Table " + number + " is not reserved!");
        }

        freeTableColor(btn, linearTable);
        shared.put(sharedKey, false);
    }

    private void freeTableColor(Button btn, LinearLayout linearTable) {
        btn.setEnabled(true);
        btn.setBackgroundColor(getResources().getColor(R.color.light_gray));

        String color = shared.getString(shared.KEY_NOT_RESERVED_TABLES);
        if (color == null) color = "BLUE";
        int backGroundColor = getColor(color);
        linearTable.setBackgroundColor(getResources().getColor(backGroundColor));
    }

    private void addButtonEventReserveAllTables(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reserveAllTables();
            }
        });
    }

    private void reserveAllTables() {
        boolean allTablesReserved = true;

        if (!isTableAlreadyReserved(shared.KEY_TABLE1)) {
            allTablesReserved = false;
            reserveTable(btnR1, linearTable1, shared.KEY_TABLE1);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE2)) {
            allTablesReserved = false;
            reserveTable(btnR2, linearTable2, shared.KEY_TABLE2);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE3)) {
            allTablesReserved = false;
            reserveTable(btnR3, linearTable3, shared.KEY_TABLE3);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE4)) {
            allTablesReserved = false;
            reserveTable(btnR4, linearTable4, shared.KEY_TABLE4);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE5)) {
            allTablesReserved = false;
            reserveTable(btnR5, linearTable5, shared.KEY_TABLE5);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE6)) {
            allTablesReserved = false;
            reserveTable(btnR6, linearTable6, shared.KEY_TABLE6);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE7)) {
            allTablesReserved = false;
            reserveTable(btnR7, linearTable7, shared.KEY_TABLE7);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE8)) {
            allTablesReserved = false;
            reserveTable(btnR8, linearTable8, shared.KEY_TABLE8);
        }
        if (!isTableAlreadyReserved(shared.KEY_TABLE9)) {
            allTablesReserved = false;
            reserveTable(btnR9, linearTable9, shared.KEY_TABLE9);
        }

        if (allTablesReserved) {
            Toast.makeText(SecondaryActivity.this, "All tables already have reservations!", Toast.LENGTH_LONG).show();
        }
    }

    private void addButtonEventChangeReservedColor(Button btn, Boolean isTableReserved) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonColor(isTableReserved);
            }
        });
    }

    private void changeButtonColor(Boolean isTableReserved) {
        String color;
        if (isTableReserved) {
            color = shared.getString(shared.KEY_RESERVED_TABLES);

            if (color == null || color.equals("RED")) {
                shared.put(shared.KEY_RESERVED_TABLES, "ORANGE");
            } else if (color.equals("ORANGE")) {
                shared.put(shared.KEY_RESERVED_TABLES, "YELLOW");
            } else if (color.equals("YELLOW")) {
                shared.put(shared.KEY_RESERVED_TABLES, "RED");
            }
        } else {
            color = shared.getString(shared.KEY_NOT_RESERVED_TABLES);

            if (color == null || color.equals("BLUE")) {
                shared.put(shared.KEY_NOT_RESERVED_TABLES, "GREEN");
            } else if (color.equals("GREEN")) {
                shared.put(shared.KEY_NOT_RESERVED_TABLES, "BROWN");
            } else if (color.equals("BROWN")) {
                shared.put(shared.KEY_NOT_RESERVED_TABLES, "BLUE");
            }
        }

        verifyTables();
    }

    private int getColor(String color) {
        switch (color) {
            case "RED":
                return R.color.design_default_color_error;
            case "ORANGE":
                return R.color.orange;
            case "YELLOW":
                return R.color.yellow;
            case "BLUE":
                return R.color.purple_500;
            case "BROWN":
                return R.color.brown;
            case "GREEN":
                return R.color.green;
            default:
                throw new Error("Invalid color!");
        }
    }
}
