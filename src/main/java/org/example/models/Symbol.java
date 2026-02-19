package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Symbol {
    private String symbol;
    public Symbol(String symbol) {
        this.symbol=symbol;
    }
}
