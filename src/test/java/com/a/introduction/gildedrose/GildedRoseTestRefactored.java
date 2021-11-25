package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseTestRefactored {

    public static final int NOT_EXPIRED_SELL_IN = 15;
    public static final int NEARING_DATE_SELL_IN = 8;
    public static final int ALMOST_THERE_SELL_IN = 2;
    public static final int DEFAULT_QUALITY = 3;
    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int EXPIRED_SELL_IN = -1;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY = 50;
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final int MIN_QUALITY = 0;

    @Test
    public void unexpiredDefaultItem_QualityDecreasesBy1() {
        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 1);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void expiredDefaultItem_QualityDecreasesBy2() {
        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 2);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void unexpiredAgedBrie_QualityIncreasesBy1() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void expiredAgedBrie_QualityIncreasesBy2() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(AGED_BRIE, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 2);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void maxQualityAgedBrie_QualityUnchanged() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELL_IN, MAX_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(AGED_BRIE, EXPIRED_SELL_IN - 1, MAX_QUALITY);
        assertItem(expectedItem, app.items[0]);
    }


    @Test
    public void unexpiredBackstagePasses_QualityIncreasesBy1() {
        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(BACKSTAGE_PASSES, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void unexpiredBackstagePasses_QualityIncreasesBy2_When_SellInLessThan11() {
        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, NEARING_DATE_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(BACKSTAGE_PASSES, NEARING_DATE_SELL_IN - 1, DEFAULT_QUALITY + 2);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void unexpiredBackstagePasses_QualityIncreasesBy3_When_SellInLessThan6() {

        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, ALMOST_THERE_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(BACKSTAGE_PASSES, ALMOST_THERE_SELL_IN - 1, DEFAULT_QUALITY + 3);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void expiredBackstagePasses_QualityBecomes0() {

        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(BACKSTAGE_PASSES, EXPIRED_SELL_IN - 1, MIN_QUALITY);
        assertItem(expectedItem, app.items[0]);
    }


    private GildedRose createGildedRoseWithOneItem(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    private void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }
}