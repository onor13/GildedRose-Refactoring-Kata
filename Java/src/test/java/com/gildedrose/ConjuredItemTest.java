package com.gildedrose;

public class ConjuredItemTest extends BasicItemTest{

  @Override
  protected int getPositiveSellInQualityChangeMultiplier() {
    return 2;
  }

  @Override protected String getItemName() {
    return GildedRose.CONJURED;
  }
}
