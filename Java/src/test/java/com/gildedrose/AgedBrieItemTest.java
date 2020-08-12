package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgedBrieItemTest {

  final static int POSITIVE_SELLIN_QUALITY_CHANGE_MULTIPLIER = 1;
  final static int NEGATIVE_SELLIN_QUALITY_CHANGE_MULTIPLIER = 2;
  final static int MAX_QUALITY                               = AgedBrieItem.MAX_QUALITY;

  private final static String agedBrieItemName = GildedRose.AGED_BRIE;

  @Test
  @DisplayName( "as sellIn decreases, the quality increases" )
  void positiveSellInTest() {
    int initialQuality = 10;
    int initialSellIn = 2;
    Item item = new Item(agedBrieItemName, initialSellIn, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, POSITIVE_SELLIN_QUALITY_CHANGE_MULTIPLIER, app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, POSITIVE_SELLIN_QUALITY_CHANGE_MULTIPLIER, app.items[0]);
  }

  @Test
  @DisplayName( "as sellIn decreases while being in the past, the quality increases" )
  void negativeSellInTest() {
    int initialQuality = 10;
    int initialSellIn = 0;
    Item item = new Item(agedBrieItemName, initialSellIn, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, NEGATIVE_SELLIN_QUALITY_CHANGE_MULTIPLIER, app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, NEGATIVE_SELLIN_QUALITY_CHANGE_MULTIPLIER, app.items[0]);
  }

  void testQuality(int initialQuality, int dayIncrement, int qualityDegradationMultiplier, Item item){
    assertEquals(initialQuality + dayIncrement * qualityDegradationMultiplier, item.quality);
  }

  @Test
  @DisplayName( "the quality should never go above " + MAX_QUALITY)
  void maximumQualityTest() {
    Item item = new Item(agedBrieItemName, 1, MAX_QUALITY );
    GildedRose app = new GildedRose(item);
    app.gotoNextDayAndUpdateQuality();
    assertEquals(MAX_QUALITY, item.quality);
    app.gotoNextDayAndUpdateQuality();
    assertEquals(MAX_QUALITY, item.quality);
  }

}
