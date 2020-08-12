package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SulfurasItemTest {

  private final int sulfurasQuality = 80;
  private final String sulfurasItemName = "Sulfuras, Hand of Ragnaros";

  @Test
  @DisplayName( "with initial sellIn above 0, neither sellIn nor quality do not change as days passes" )
  void testPositiveSellIn() {
    int initialSellIn = 2;
    Item item = new Item(sulfurasItemName, initialSellIn, sulfurasQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn, app.items[0].sellIn);
    assertEquals(sulfurasQuality, app.items[0].quality);
  }

  @Test
  @DisplayName( "with initial sellIn below 0, neither sellIn nor quality do not change as days passes" )
  void testNegativeSellIn() {
    int initialSellIn = -1;
    Item item = new Item(sulfurasItemName, initialSellIn, sulfurasQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn, app.items[0].sellIn);
    assertEquals(sulfurasQuality, app.items[0].quality);
  }
}
