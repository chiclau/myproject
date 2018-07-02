package com.liu.test;
/**
 * 想判断两个对象的属性值是否相同，要对equals(Object obj)方法覆盖。
 * @author Administrator
 *
 */
public class Car {
		private String  carName;
		private boolean sale;
		
		public Car(String c1,boolean isSale) {
			carName=c1;
			sale=isSale;
		}

		public String getCarName() {
			return carName;
		}

		public void setCarName(String carName) {
			this.carName = carName;
		}

		public boolean getSale() {
			return sale;
		}

		public void setSale(boolean sale) {
			this.sale = sale;
		}

		
		public String toString() { 			//覆盖toString()方法
			if(sale) return (carName+"已卖出");
			else {
				return (carName+"未卖出！");
			}
		}
		
		public boolean equals(Object obj) {//覆盖equals()方法
			if(!(obj instanceof Car) ) return false;
			Car c=(Car) obj;
			return (carName.equals(c.getCarName())&&(sale==c.getSale()));
		}
		
}
