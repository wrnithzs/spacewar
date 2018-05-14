package f2.spw;
public class Endscore{

	 private int enm_p1 = 0;
	 private int boss_p1 = 0;

	 private int enm_p2 = 0;
	 private int boss_p2 = 0;

	public int getp1(int a){

		if(a == 1){
			return enm_p1;
		}
		else {
			return boss_p1;
		}
	}
	public int getp2(int a){

		if(a == 1){
			return enm_p2;
		}
		else{
			return boss_p2;
		}
	}
	public void countP1Enemy (int a){
		if(a == 1){
			enm_p1++;
		}
		else {
			boss_p1++;
		}

	}
	public void countP2Enemy (int a){

		if(a == 1){
			enm_p2++;
		}
		else{
			boss_p2++;	
		}

	}


}