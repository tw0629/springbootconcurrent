package com.tian.concurrent.condition;

/**
 * @author David Tian
 * @desc
 * @since 2020-07-17 17:52
 */
public enum Season2 {

    SPRING() {
        @Override
        public Season2 getNextSeason() {
            return SUMMER;
        }
    }, SUMMER() {
        @Override
        public Season2 getNextSeason() {
            return AUTUMN;
        }
    }, AUTUMN() {
        @Override
        public Season2 getNextSeason() {
            return WINTER;
        }
    }, WINTER() {
        @Override
        public Season2 getNextSeason() {
            return SPRING;
        }
    };

    public abstract Season2 getNextSeason();

    Season2() {
    }
}
