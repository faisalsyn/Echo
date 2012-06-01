/* EchoClient.java
 *
 * Copyright (c) 2000 Sean Walton and Macmillan Publishers.  Use may be in
 * whole or in part in accordance to the General Public License (GPL).
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
*/

/*****************************************************************************/
/*** EchoClient.java                                                       ***/
/***                                                                       ***/
/*****************************************************************************/

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EchoClient
{
	public static void main(String[] args)
	{
		int size = 1024;
		int trials = 1000;
		
		if ( args.length < 3 ) {
			System.out.println ("USAGE: java EchoClient [server-address] [packet-size] [number-of-packets]");
			System.exit(0);
		}
		
		size = Integer.parseInt(args[1]);
		trials = Integer.parseInt(args[2]);
		
		try
		{
			Socket s = new Socket(args[0], 9999);
			BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter w = new PrintWriter(s.getOutputStream(), true);
			BufferedReader con = new BufferedReader(new InputStreamReader(System.in));
			String line;
			
			String buffer = "";
			for ( int i = 0; i < size ; i++ ){ // should be size - size of ts
				buffer += "0";
			}
			
			for ( int i = 0 ; i < trials ; i++ ){
				//Date d = new Date();
				w.println(System.nanoTime() + ":" + buffer);
				line = r.readLine();
				if ( line != null )
					//d = new Date();
					System.out.println(System.nanoTime() - Long.parseLong(line));
			}
			
			w.println("bye");
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}
}