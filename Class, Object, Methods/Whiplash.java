import java.util.Scanner;

public class Whiplash {
    public static void main(String[] args) {
        // เนื้อเพลง
        String text = "one look, give 'em whiplash\n"
        + "Beat drop with a big flash\n"
        + "집중해 좀 더 think fast\n"
        + "이유 넌 이해 못 해 uh\n"
        + "왜 이제야 know I did that, uh\n"
        + "Day one, know I been bad, uh\n"
        + "Don't stop 흔들린 채\n"
        + "무리해도 can't touch that (can't touch that)\n"
        + "Under pressure, body sweating, can you focus? (Yeah)\n"
        + "어디서나 거침없어 I'm the coldest\n"
        + "오직 나만이 이 판을 바꿀 changer\n"
        + "Just close your eyes, breathe in and visualize\n"
        + "One look, give 'em whiplash\n"
        + "Beat drop with a big flash\n"
        + "집중해 좀 더 think fast\n"
        + "이유 넌 이해 못 해 uh\n"
        + "왜 이제야 know I did that (yeah)\n"
        + "Day one, know I been bad\n"
        + "Don't stop 흔들린 채 (yeah)\n"
        + "무리해도 can't touch that (can't touch that)\n"
        + "Whip-whiplash, whip-whiplash\n"
        + "Whip-whiplash, whip-whiplash\n"
        + "Whip-whiplash, whip-whiplash\n"
        + "Whip-whiplash, one look, give 'em whiplash\n"
        + "Fancy 이건 참 화려해\n"
        + "It's glowing and it's flashy (yeah)\n"
        + "알아, 적당함이 뭔지 keep it classy\n"
        + "따라 하지 넌 또 하나부터 열까지 (yeah)\n"
        + "아닌척하지 (yeah)\n"
        + "Under pressure, body sweating, can you focus? (Hoo)\n"
        + "I deliver, I can promise, I'm the coldest (cold)\n"
        + "외면해도 소용없지 don't you blow it?\n"
        + "Just close your eyes, breathe in and visualize\n"
        + "시작되는 순간 끝나버린 판단\n"
        + "기횐 오직 one time, unforgettable\n"
        + "찾아낸 the right light 사라지지 않아\n"
        + "눈에 새긴 one time, unforgettable\n"
        + "One look, give 'em whiplash (sheesh)\n"
        + "Beat drop with a big flash\n"
        + "집중해 좀 더 think fast\n"
        + "이유 넌 이해 못 해 uh\n"
        + "왜 이제야 know I did that, uh (yeah)\n"
        + "Day one, know I been bad, uh\n"
        + "Don't stop 흔들린 채 (yeah)\n"
        + "무리해도 can't touch that (can't touch that)\n"
        + "I'm the highlight 비춰 red light\n"
        + "Yeah, tonight it's all about me (all about me)\n"
        + "You know it's my site 거친 next sigh\n"
        + "만들어갈 history\n"
        + "One look, give 'em whiplash (whip-whip, sheesh)\n"
        + "Beat drop with a big flash (yeah)\n"
        + "따라와 어서 move fast (move, move, move)\n"
        + "이유 넌 이해 못 해 uh (yeah)\n"
        + "왜 이제야 know I did that, uh (did that, yeah)\n"
        + "Day one, know I been bad, uh (been bad)\n"
        + "Don't stop 흔들린 채 (yeah)\n"
        + "다가와도 can't touch that (can't touch that)\n"
        + "Whip-whiplash, whip-whiplash\n"
        + "Whip-whiplash, whip-whiplash\n"
        + "Whip-whiplash, whip-whiplash\n"
        + "Whip-whiplash, one look, give 'em whiplash";

        Scanner input = new Scanner(System.in);
        String rawInput = input.nextLine();  
        input.close();

        
        String keyword = rawInput.replace("\"", "").replace("“", "").replace("”", "").toLowerCase();

        String lowerText = text.toLowerCase();
        int count = 0;

        for (int i = 0; i <= lowerText.length() - keyword.length(); i++) {
            String sub = lowerText.substring(i, i + keyword.length());
            if (sub.equals(keyword)) {
                count++;
            }
        }

        
        System.out.printf("มี \"%s\" ปรากฏอยู่ %d ครั้ง", keyword, count);

    }
}
