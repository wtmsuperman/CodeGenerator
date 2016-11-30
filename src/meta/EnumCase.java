package meta;

import meta.Meta;

/**
 * Created by Administrator on 2016/11/30.
 */
public class EnumCase extends Meta{
        private String name;
        private int value;

        public EnumCase() {
        }

        public EnumCase(String name,int value)
        {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
}
