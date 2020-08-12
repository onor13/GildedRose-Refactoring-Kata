package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BasicItemTest {

  protected abstract int getPositiveSellInQualityChangeMultiplier();

  protected abstract String getItemName();

  protected int getNegativeSellInQualityChangeMultiplier(){
    return getPositiveSellInQualityChangeMultiplier() * 2;
  }

  @Test
  @DisplayName( "as sellIn decreases, the quality decreases" )
  void positiveSellInTest() {
    int initialQuality = 10;
    int initialSellIn = 2;
    Item item = new Item(getItemName(), initialSellIn, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, getPositiveSellInQualityChangeMultiplier(), app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, getPositiveSellInQualityChangeMultiplier(), app.items[0]);
  }

  @Test
  @DisplayName( "as sellIn decreases while being in the past, the quality decreases" )
  void negativeSellInTest() {
    int initialQuality = 10;
    int initialSellIn = 0;
    Item item = new Item(getItemName(), 0, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, getNegativeSellInQualityChangeMultiplier(), app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, getNegativeSellInQualityChangeMultiplier(), app.items[0]);
  }

  @Test
  @DisplayName( "with sellIn above 0, the quality never goes below 0" )
  void minimalQualityTest() {
    Item item = new Item(getItemName(), 2, 0);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(0, item.quality);
  }

  @Test
  @DisplayName( "with sellIn below 0, the quality never goes below 0" )
  void minimalQualityTest2() {
    Item item = new Item(getItemName(), 0, 0);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(0, item.quality);
  }

  void testQuality(int initialQuality, int dayIncrement, int qualityDegradationMultiplier, Item item) {
    assertEquals(initialQuality - dayIncrement * qualityDegradationMultiplier, item.quality);
  }
}
